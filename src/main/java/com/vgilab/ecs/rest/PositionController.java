package com.vgilab.ecs.rest;

import com.vgilab.ecs.mapper.PositionBatchModelMapper;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.persistence.entity.Position;
import com.vgilab.ecs.persistence.entity.PositionInTime;
import com.vgilab.ecs.persistence.repositories.PositionInTimeRepository;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import java.util.Calendar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ljzhang
 */
@Component
@RestController
public class PositionController {

    private final PositionRepository positionRepository;

    private final PositionInTimeRepository positionInTimeRepository;

    @Autowired
    public PositionController(PositionRepository positionRepository, PositionInTimeRepository positionInTimeRepository) {
        this.positionRepository = positionRepository;
        this.positionInTimeRepository = positionInTimeRepository;
    }

    @RequestMapping(value = "/positions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionBatchResource> submitPositions(@RequestBody PositionBatchResource positionBatch) {
        if (null != positionBatch && null != positionBatch.getPositions()) {
            try {
                final ModelMapper positionBatchResourceToPositionInTimeEntityModellMapper = PositionBatchModelMapper.getResourceToPositionInTimeEntityModellMapper();
                final ModelMapper positionResourceToPositionInTimeEntityModellMapper = PositionModelMapper.getResourceToPositionInTimeEntityModellMapper();
                final ModelMapper positionResourceToPositionEntityModellMapper = PositionModelMapper.getResourceToPositionEntityModellMapper();
                positionBatch.getPositions().stream().filter(curPositionDto -> null != curPositionDto.getAltitude()).map((curPositionDto) -> {
                    Position position = this.positionRepository.findByLongitudeAndLatitude(curPositionDto.getLongitude(), curPositionDto.getLatitude());
                    if (null != position) {
                        Double averageAltitude = 0d;
                        for( final PositionInTime curPositionInTime : position.getPositionsInTime()) {
                            averageAltitude += curPositionInTime.getAltitude();
                        }
                        averageAltitude += curPositionDto.getAltitude();
                        position.setAverageAltitude(averageAltitude / (position.getPositionsInTime().size() + 1));
                    } else {
                        position = new Position();
                        position.setCreatedOn(Calendar.getInstance());
                        position.setAverageAltitude(curPositionDto.getAltitude());
                        positionResourceToPositionEntityModellMapper.map(curPositionDto, position);
                    }
                    position.setModifiedOn(Calendar.getInstance());
                    final PositionInTime positionInTime = new PositionInTime();
                    positionInTime.setPosition(position);
                    positionBatchResourceToPositionInTimeEntityModellMapper.map(positionBatch, positionInTime);
                    positionResourceToPositionInTimeEntityModellMapper.map(curPositionDto, positionInTime);
                    positionInTime.setCreatedOn(Calendar.getInstance());
                    // this.positionInTimeRepository.save(positionInTime);
                    position.getPositionsInTime().add(positionInTime);
                    return this.positionRepository.save(position);
                }).forEach((position) -> {
                });
            } catch (Exception ex) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionBatch, HttpStatus.OK);
    }
}

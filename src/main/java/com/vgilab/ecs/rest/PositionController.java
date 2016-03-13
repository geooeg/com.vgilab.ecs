package com.vgilab.ecs.rest;

import com.vgilab.ecs.mapper.PositionBatchModelMapper;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.persistence.entity.Position;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
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

    @Autowired
    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @RequestMapping(value = "/positions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionBatchResource> submitPositions(@RequestBody PositionBatchResource positionBatch) {
        if (null != positionBatch && null != positionBatch.getPositions()) {
            try {
                final ModelMapper positionBatchModellMapper = PositionBatchModelMapper.getDtoToEntityModellMapper();
                final ModelMapper positionModellMapper = PositionModelMapper.getDtoToEntityModellMapper();
                final List<Position> postions = new LinkedList<>();
                positionBatch.getPositions().stream().map((curPositionDto) -> {
                    final Position position = new Position();
                    positionBatchModellMapper.map(positionBatch, position);
                    positionModellMapper.map(curPositionDto, position);
                    // TODO convert the data
                    position.setCreatedOn(Calendar.getInstance());
                    return position;
                }).forEach((position) -> {
                    postions.add(position);
                });
                this.positionRepository.save(postions);
            } catch (Exception ex) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionBatch, HttpStatus.OK);
    }
}

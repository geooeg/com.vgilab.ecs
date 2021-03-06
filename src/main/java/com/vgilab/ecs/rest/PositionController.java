package com.vgilab.ecs.rest;

import com.vgilab.ecs.rest.resource.PositionBatchResource;
import com.vgilab.ecs.mapper.PositionBatchModelMapper;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.persistence.entity.PositionEntity;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private final Logger logger = LoggerFactory.getLogger(PositionController.class);

    private final PositionRepository positionRepository;

    private final TripRepository tripRepository;

    @Autowired
    public PositionController(PositionRepository positionRepository, TripRepository tripRepository) {
        this.positionRepository = positionRepository;
        this.tripRepository = tripRepository;
    }

    @RequestMapping(value = "/positions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionBatchResource> submitPositions(@RequestBody PositionBatchResource positionBatch) {
        if (null != positionBatch && null != positionBatch.getPositions()) {
            final String tripId = positionBatch.getTripId();
            if (StringUtils.isNotEmpty(tripId) && this.tripRepository.exists(tripId)) {
                final TripEntity tripEntity = this.tripRepository.findOne(tripId);
                try {
                    final ModelMapper positionBatchResourceToPositionInTimeEntityModellMapper = PositionBatchModelMapper.getResourceToPositionInTimeEntityModellMapper();
                    final ModelMapper positionResourceToPositionInTimeEntityModellMapper = PositionModelMapper.getResourceToPositionInTimeEntityModellMapper();
                    final ModelMapper positionResourceToPositionEntityModellMapper = PositionModelMapper.getResourceToPositionEntityModellMapper();
                    positionBatch.getPositions().stream().filter(curPositionDto -> null != curPositionDto.getAltitude()).map((curPositionDto) -> {
                        PositionEntity position = this.positionRepository.findByLongitudeAndLatitude(curPositionDto.getLongitude(), curPositionDto.getLatitude());
                        if (null != position) {
                            Double averageAltitude = 0d;
                            for (final PositionInTimeEntity curPositionInTime : position.getPositionsInTime()) {
                                averageAltitude += curPositionInTime.getAltitude();
                            }
                            averageAltitude += curPositionDto.getAltitude();
                            position.setAverageAltitude(averageAltitude / (position.getPositionsInTime().size() + 1));
                        } else {
                            position = new PositionEntity();
                            position.setAverageAltitude(curPositionDto.getAltitude());
                            positionResourceToPositionEntityModellMapper.map(curPositionDto, position);
                        }
                        final PositionInTimeEntity positionInTime = new PositionInTimeEntity();
                        positionInTime.setPosition(position);
                        positionInTime.setTrip(tripEntity);
                        final DateTime startedOn = new DateTime(tripEntity.getStartedOn());
                        final DateTime trackedOn = startedOn.plusSeconds(curPositionDto.getTrackedOn().intValue());
                        positionInTime.setTrackedOn(trackedOn.toCalendar(Locale.getDefault()));
                        positionBatchResourceToPositionInTimeEntityModellMapper.map(positionBatch, positionInTime);
                        positionResourceToPositionInTimeEntityModellMapper.map(curPositionDto, positionInTime);
                        position.getPositionsInTime().add(positionInTime);
                        return this.positionRepository.save(position);
                    }).forEach((position) -> {
                    });
                } catch (Exception ex) {
                    logger.error("Wrong request for trip: " + tripId, ex);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                logger.error("Missing trip for positions: " + tripId);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            logger.error("Missing content for tag");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionBatch, HttpStatus.OK);
    }
}

package com.vgilab.ecs.rest;

import com.vgilab.ecs.enums.Emoticon;
import com.vgilab.ecs.enums.MediaEvent;
import com.vgilab.ecs.mapper.MediaModelMapper;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.persistence.entity.MediaEntity;
import com.vgilab.ecs.persistence.entity.PositionEntity;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.MediaRepository;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.CreateMediaResource;
import com.vgilab.ecs.rest.resource.PositionResource;
import com.vgilab.ecs.rest.response.CreateMediaResponse;
import org.apache.commons.lang3.StringUtils;
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
 * @author smuellner
 */
@Component
@RestController
public class MediaController {

    private final Logger logger = LoggerFactory.getLogger(MediaController.class);

    private final PositionRepository positionRepository;

    private final TripRepository tripRepository;

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaController(PositionRepository positionRepository, TripRepository tripRepository, MediaRepository mediaRepository) {
        this.positionRepository = positionRepository;
        this.tripRepository = tripRepository;
        this.mediaRepository = mediaRepository;
    }

    @RequestMapping(value = "/create_media", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateMediaResponse> createMedia(@RequestBody CreateMediaResource mediaResource) {
        final CreateMediaResponse createMediaResponse = new CreateMediaResponse();
        if (null != mediaResource && null != mediaResource.getPosition()) {
            final String tripId = mediaResource.getTripId();
            final PositionResource positionResource = mediaResource.getPosition();
            if (StringUtils.isNotEmpty(tripId) && this.tripRepository.exists(tripId)) {
                final TripEntity tripEntity = this.tripRepository.findOne(tripId);
                try {
                    final ModelMapper mediaResourceToEntityModelMapper = MediaModelMapper.getResourceToEntityModellMapper();
                    final ModelMapper positionResourceToPositionEntityModellMapper = PositionModelMapper.getResourceToPositionEntityModellMapper();
                    MediaEntity media = new MediaEntity();
                    PositionEntity position = this.positionRepository.findByLongitudeAndLatitude(positionResource.getLongitude(), positionResource.getLatitude());
                    if (null != position) {
                        Double averageAltitude = 0d;
                        for (final PositionInTimeEntity curPositionInTime : position.getPositionsInTime()) {
                            averageAltitude += curPositionInTime.getAltitude();
                        }
                        averageAltitude += positionResource.getAltitude();
                        position.setAverageAltitude(averageAltitude / (position.getPositionsInTime().size() + 1));
                    } else {
                        position = new PositionEntity();
                        position.setAverageAltitude(positionResource.getAltitude());
                        positionResourceToPositionEntityModellMapper.map(positionResource, position);
                    }
                    mediaResourceToEntityModelMapper.map(mediaResource, media);
                    media.setPosition(position);
                    media.setTrip(tripEntity);
                    try {
                        media.setEvent(MediaEvent.valueOf(mediaResource.getEvent()));
                    } catch (Exception ex) {
                        logger.error("MediaEvent Wrong" + mediaResource.getEvent(), ex);
                        media.setEvent(MediaEvent.UNKOWN);
                    }
                    media = this.mediaRepository.save(media);
                    tripEntity.getMedias().add(media);
                    this.tripRepository.save(tripEntity);
                    createMediaResponse.setMediaId(media.getId());
                } catch (Exception ex) {
                    logger.error("Wrong request for setting media of trip: " + tripId, ex);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                logger.error("Missing trip for media: " + tripId);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            logger.error("Missing content for media");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createMediaResponse, HttpStatus.OK);
    }
}

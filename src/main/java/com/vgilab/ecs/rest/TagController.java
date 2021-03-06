package com.vgilab.ecs.rest;

import com.vgilab.ecs.enums.ContentType;
import com.vgilab.ecs.enums.TagCategory;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.mapper.TagModelMapper;
import com.vgilab.ecs.persistence.entity.PositionEntity;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TagEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vgilab.ecs.persistence.repositories.TagRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.CreateTagResource;
import com.vgilab.ecs.rest.resource.PositionResource;
import com.vgilab.ecs.rest.response.CreateTagResponse;
import java.util.Calendar;
import java.util.Locale;
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
public class TagController {

    private final Logger logger = LoggerFactory.getLogger(TagController.class);
    
    private final PositionRepository positionRepository;

    private final TripRepository tripRepository;

    private final TagRepository tagRepository;

    @Autowired
    public TagController(PositionRepository positionRepository, TripRepository tripRepository, TagRepository tagRepository) {
        this.positionRepository = positionRepository;
        this.tripRepository = tripRepository;
        this.tagRepository = tagRepository;
    }

    @RequestMapping(value = "/create_tag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateTagResponse> createTag(@RequestBody CreateTagResource tagResource) {
        final CreateTagResponse createTagResponse = new CreateTagResponse();
        if (null != tagResource && null != tagResource.getPosition()) {
            final String tripId = tagResource.getTripId();
            final PositionResource positionResource = tagResource.getPosition();
            if (StringUtils.isNotEmpty(tripId) && this.tripRepository.exists(tripId)) {
                final TripEntity tripEntity = this.tripRepository.findOne(tripId);
                try {
                    final ModelMapper tagResourceToEntityModelMapper = TagModelMapper.getResourceToEntityModellMapper();
                    final ModelMapper positionResourceToPositionEntityModellMapper = PositionModelMapper.getResourceToPositionEntityModellMapper();
                    TagEntity tag = new TagEntity();
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
                    tagResourceToEntityModelMapper.map(tagResource, tag);
                    tag.setPosition(this.positionRepository.save(position));
                    tag.setTrip(tripEntity);
                    tag.setTrackedOn(tagResource.getTrackedOn()!= null ? tagResource.getTrackedOn().toCalendar(Locale.ENGLISH) : Calendar.getInstance());
                    try {
                        tag.setContentType(Enum.valueOf(ContentType.class, tagResource.getContentType()));
                    } catch (Exception ex) {
                        logger.error("Tag Content Type Wrong" + tagResource.getContentType(), ex);
                        tag.setContentType(ContentType.QR_CODE);
                    }
                    try {
                        tag.setCategory(Enum.valueOf(TagCategory.class, tagResource.getCategory()));
                    } catch (Exception ex) {
                        logger.error("Tag Category Wrong" + tagResource.getCategory(), ex);
                        tag.setCategory(TagCategory.GENERAL);
                    }
                    tag = this.tagRepository.save(tag);
                    tripEntity.getTags().add(tag);
                    this.tripRepository.save(tripEntity);
                    createTagResponse.setTagId(tag.getId());
                } catch (Exception ex) {
                    logger.error("Wrong request for tagging trip: " + tripId, ex);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                logger.error("Missing trip for tag: " + tripId);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            logger.error("Missing content for tag");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createTagResponse, HttpStatus.OK);
    }
}

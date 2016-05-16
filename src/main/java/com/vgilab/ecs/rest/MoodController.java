package com.vgilab.ecs.rest;

import com.vgilab.ecs.enums.Emoticon;
import com.vgilab.ecs.mapper.MoodModelMapper;
import com.vgilab.ecs.mapper.PositionBatchModelMapper;
import com.vgilab.ecs.mapper.PositionModelMapper;
import com.vgilab.ecs.persistence.entity.MoodEntity;
import com.vgilab.ecs.persistence.entity.PositionEntity;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.MoodRepository;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.CreateMoodResource;
import com.vgilab.ecs.rest.resource.PositionBatchResource;
import com.vgilab.ecs.rest.resource.PositionResource;
import com.vgilab.ecs.rest.response.CreateMoodResponse;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
 * @author smuellner
 */
@Component
@RestController
public class MoodController {

    private final PositionRepository positionRepository;

    private final TripRepository tripRepository;

    private final MoodRepository moodRepository;

    @Autowired
    public MoodController(PositionRepository positionRepository, TripRepository tripRepository, MoodRepository moodRepository) {
        this.positionRepository = positionRepository;
        this.tripRepository = tripRepository;
        this.moodRepository = moodRepository;
    }

    @RequestMapping(value = "/create_mood", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateMoodResponse> createMood(@RequestBody CreateMoodResource moodResource) {
        final CreateMoodResponse createMoodResponse = new CreateMoodResponse();
        if (null != moodResource && null != moodResource.getPosition()) {
            final String tripId = moodResource.getTripId();
            final PositionResource positionResource = moodResource.getPosition();
            if (StringUtils.isNotEmpty(tripId) && this.tripRepository.exists(tripId)) {
                final TripEntity tripEntity = this.tripRepository.findOne(tripId);
                try {
                    final ModelMapper moodResourceToEntityModelMapper = MoodModelMapper.getResourceToEntityModellMapper();
                    final ModelMapper positionResourceToPositionEntityModellMapper = PositionModelMapper.getResourceToPositionEntityModellMapper();
                    final MoodEntity mood = new MoodEntity();
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
                    moodResourceToEntityModelMapper.map(moodResource, mood);
                    mood.setPosition(position);
                    mood.setTrip(tripEntity);
                    mood.setEmoticon(Emoticon.valueOf(moodResource.getEmoticon()));
                    createMoodResponse.setMoodId(this.moodRepository.save(mood).getId());
                } catch (Exception ex) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createMoodResponse, HttpStatus.OK);
    }
}

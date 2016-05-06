package com.vgilab.ecs.rest;

import com.vgilab.ecs.persistence.entity.DeviceEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.DeviceRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.StopTripResource;
import com.vgilab.ecs.rest.resource.CreateTripResource;
import com.vgilab.ecs.rest.response.StopTripResponse;
import com.vgilab.ecs.rest.response.CreateTripResponse;
import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;
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
public class TripController {

    private final TripRepository tripRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public TripController(TripRepository tripRepository, DeviceRepository deviceRepository) {
        this.tripRepository = tripRepository;
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(value = "/create_trip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateTripResponse> createTrip(@RequestBody CreateTripResource createTripResource) {
        final CreateTripResponse createTripResponse = new CreateTripResponse();
        if (null != createTripResource) {
            final String deviceId = createTripResource.getDeviceId();
            if (StringUtils.isNotEmpty(deviceId) && this.deviceRepository.exists(deviceId)) {
                final DeviceEntity deviceEntity = this.deviceRepository.findOne(deviceId);
                TripEntity tripEntity = new TripEntity();
                tripEntity.setDevice(deviceEntity);
                tripEntity.setStartedOn(createTripResource.getStartTime() != null ? createTripResource.getStartTime() :  Calendar.getInstance());
                tripEntity = this.tripRepository.save(tripEntity);
                createTripResponse.setTripId(tripEntity.getId());
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createTripResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/stop_trip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StopTripResponse> stopTrip(@RequestBody StopTripResource stopTripResource) {
        final StopTripResponse stopTripResponse = new StopTripResponse();
        if (null != stopTripResource) {
            final String tripId = stopTripResource.getTripId();
            if (StringUtils.isNotEmpty(tripId) && this.tripRepository.exists(tripId)) {
                final TripEntity tripEntity = this.tripRepository.findOne(tripId);
                tripEntity.setStoppedOn(stopTripResource.getEndTime() != null ? stopTripResource.getEndTime() :  Calendar.getInstance());
                this.tripRepository.save(tripEntity);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stopTripResponse, HttpStatus.OK);
    }
}

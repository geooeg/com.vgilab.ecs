package com.vgilab.ecs.rest;

import com.vgilab.ecs.mapper.DeviceModelMapper;
import com.vgilab.ecs.persistence.entity.AuthorizationCodeEntity;
import com.vgilab.ecs.persistence.repositories.DeviceRepository;
import com.vgilab.ecs.persistence.repositories.AuthorizationCodeRepository;
import com.vgilab.ecs.rest.resource.CreateDeviceWithAuthorizationResource;
import com.vgilab.ecs.rest.response.CreateDeviceResponse;
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
import com.vgilab.ecs.persistence.entity.DeviceEntity;
import com.vgilab.ecs.rest.resource.DeviceResource;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author smuellner
 */
@Component
@RestController
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final AuthorizationCodeRepository authorizationCodeRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository, AuthorizationCodeRepository authorizationCodeRepository) {
        this.deviceRepository = deviceRepository;
        this.authorizationCodeRepository = authorizationCodeRepository;
    }

    @RequestMapping(value = "/create_device_with_auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDeviceResponse> createDeviceWithAuthorization(@RequestBody CreateDeviceWithAuthorizationResource deviceWithAuthorizationResource) {
        final CreateDeviceResponse createDeviceResponse = new CreateDeviceResponse();
        if (null != deviceWithAuthorizationResource) {
            final String authorizationCode = deviceWithAuthorizationResource.getAuthorizationCode();
            if (StringUtils.isNotEmpty(authorizationCode) && this.authorizationCodeRepository.exists(authorizationCode)) {
                final AuthorizationCodeEntity authorizationCodeEntity = this.authorizationCodeRepository.findOne(authorizationCode);
                if (Calendar.getInstance().after(authorizationCodeEntity.getValidUntilTime())) {
                    final ModelMapper deviceModelMapper = DeviceModelMapper.getResourceToDeviceEntityModellMapper();
                    DeviceEntity deviceEntity = this.deviceRepository.findByMakerAndIdentifierForVendor(deviceWithAuthorizationResource.getMaker(), deviceWithAuthorizationResource.getIdentifierForVendor());
                    if (null == deviceEntity) {
                        deviceEntity = new DeviceEntity();
                    }
                    deviceModelMapper.map(deviceWithAuthorizationResource, deviceEntity);
                    deviceEntity = this.deviceRepository.save(deviceEntity);
                    createDeviceResponse.setDeviceId(deviceEntity.getId());
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createDeviceResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/create_device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDeviceResponse> createDevice(@RequestBody DeviceResource deviceResource) {
        final CreateDeviceResponse createDeviceResponse = new CreateDeviceResponse();
        if (null != deviceResource) {
            final ModelMapper deviceModelMapper = DeviceModelMapper.getResourceToDeviceEntityModellMapper();
            DeviceEntity deviceEntity = this.deviceRepository.findByMakerAndIdentifierForVendor(deviceResource.getMaker(), deviceResource.getIdentifierForVendor());
            if (null == deviceEntity) {
                deviceEntity = new DeviceEntity();
            }
            deviceModelMapper.map(deviceResource, deviceEntity);
            deviceEntity = this.deviceRepository.save(deviceEntity);
            createDeviceResponse.setDeviceId(deviceEntity.getId());
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createDeviceResponse, HttpStatus.OK);
    }
}

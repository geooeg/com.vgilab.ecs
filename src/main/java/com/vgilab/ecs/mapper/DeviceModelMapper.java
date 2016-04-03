package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.DeviceEntity;
import com.vgilab.ecs.rest.resource.DeviceResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author smuellner
 */
public class DeviceModelMapper {
    
    public static ModelMapper getResourceToDeviceEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<DeviceResource, DeviceEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
                this.map().setMaker(this.source.getMaker());
                this.map().setModel(this.source.getModel());
                this.map().setSoftware(this.source.getSoftware());
            }
        });
        return modelMapper;
    }
}

package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.MediaEntity;
import com.vgilab.ecs.rest.resource.CreateMediaResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author smuellner
 */
public class MediaModelMapper {
    
    public static ModelMapper getResourceToEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<CreateMediaResource, MediaEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
            }
        });
        return modelMapper;
    }
}

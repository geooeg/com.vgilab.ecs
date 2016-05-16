package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.TagEntity;
import com.vgilab.ecs.rest.resource.CreateTagResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author smuellner
 */
public class TagModelMapper {
    
    public static ModelMapper getResourceToEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<CreateTagResource, TagEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
                this.map().setContent(this.source.getContent());
                this.map().setTrackedOn(this.source.getTrackedOn());
            }
        });
        return modelMapper;
    }
}

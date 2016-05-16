
package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.MoodEntity;
import com.vgilab.ecs.rest.resource.CreateMoodResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author smuellner
 */
public class MoodModelMapper {
    
    public static ModelMapper getResourceToEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<CreateMoodResource, MoodEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
                this.map().setMessage(this.source.getMessage());
                this.map().setTrackedOn(this.source.getTrackedOn());
            }
        });
        return modelMapper;
    }
}

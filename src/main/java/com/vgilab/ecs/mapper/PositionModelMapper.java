package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.PositionEntity;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.rest.resource.PositionResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author ljzhang
 */
public class PositionModelMapper {
    
    public static ModelMapper getResourceToPositionEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<PositionResource, PositionEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
            }
        });
        return modelMapper;
    }
    
    public static ModelMapper getResourceToPositionInTimeEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<PositionResource, PositionInTimeEntity>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
            }
        });
        return modelMapper;
    }
}

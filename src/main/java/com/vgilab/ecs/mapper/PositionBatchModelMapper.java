package com.vgilab.ecs.mapper;

import com.vgilab.ecs.persistence.entity.Position;
import com.vgilab.ecs.persistence.entity.PositionInTime;
import com.vgilab.ecs.rest.PositionBatchResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 *
 * @author ljzhang
 */
public class PositionBatchModelMapper {
    
    public static ModelMapper getResourceToPositionEntityModellMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<PositionBatchResource, Position>() {
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
        modelMapper.addMappings(new PropertyMap<PositionBatchResource, PositionInTime>() {
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

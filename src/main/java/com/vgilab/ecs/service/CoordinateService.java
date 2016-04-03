
package com.vgilab.ecs.service;

import com.vgilab.ecs.persistence.entity.PositionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.vgilab.ecs.persistence.repositories.PositionRepository;

/**
 *
 * @author ljzhang
 */
@Service("coordinateService")
public class CoordinateService {
    
    @Autowired
    PositionRepository coordinateRepository;

    public PositionEntity save(PositionEntity coordinate) {
        return this.coordinateRepository.save(coordinate);
    }

    public Page<PositionEntity> findAll(PageRequest pageRequest) {
        return this.coordinateRepository.findAll(pageRequest);
    }

    public long count() {
        return this.coordinateRepository.count();
    }

    public PositionEntity findOne(Long id) {
        return this.coordinateRepository.findOne(id);
    }
}

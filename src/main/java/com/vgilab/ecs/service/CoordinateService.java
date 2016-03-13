
package com.vgilab.ecs.service;

import com.vgilab.ecs.persistence.entity.Position;
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

    public Position save(Position coordinate) {
        return this.coordinateRepository.save(coordinate);
    }

    public Page<Position> findAll(PageRequest pageRequest) {
        return this.coordinateRepository.findAll(pageRequest);
    }

    public long count() {
        return this.coordinateRepository.count();
    }

    public Position findOne(Long id) {
        return this.coordinateRepository.findOne(id);
    }
}

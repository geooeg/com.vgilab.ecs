package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.PositionEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ljzhang
 */
@Repository
public interface PositionRepository extends PagingAndSortingRepository<PositionEntity, Long>, QueryDslPredicateExecutor<PositionEntity>  {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PositionEntity findOne(Long id);
    
    /**
     *
     * @param longitude
     * @param latitude
     * @return
     */
    public PositionEntity findByLongitudeAndLatitude(Double longitude, Double latitude);

    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<PositionEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<PositionEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<PositionEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public PositionEntity save(PositionEntity persisted);

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean exists(Long id);

    /**
     *
     * @return
     */
    @Override
    public long count();
}

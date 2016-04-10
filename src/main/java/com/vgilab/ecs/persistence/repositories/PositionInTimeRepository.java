package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
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
public interface PositionInTimeRepository extends PagingAndSortingRepository<PositionInTimeEntity, Long>, QueryDslPredicateExecutor<PositionInTimeEntity>  {

    /**
     *
     * @param tripEntity
     * @return
     */
    public List<PositionInTimeEntity> findByTripOrderByTrackedOnAsc(final TripEntity tripEntity);
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public PositionInTimeEntity findOne(Long id);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<PositionInTimeEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<PositionInTimeEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<PositionInTimeEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public PositionInTimeEntity save(PositionInTimeEntity persisted);

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

package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.TripEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author smuellner
 */
public interface TripRepository extends PagingAndSortingRepository<TripEntity, String>, QueryDslPredicateExecutor<TripEntity>  {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public TripEntity findOne(String id);

    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<TripEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<TripEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<TripEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public TripEntity save(TripEntity persisted);

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean exists(String id);

    /**
     *
     * @return
     */
    @Override
    public long count();
}
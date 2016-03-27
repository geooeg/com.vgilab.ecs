package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.PositionInTime;
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
public interface PositionInTimeRepository extends PagingAndSortingRepository<PositionInTime, Long>, QueryDslPredicateExecutor<PositionInTime>  {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PositionInTime findOne(Long id);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<PositionInTime> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<PositionInTime> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<PositionInTime> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public PositionInTime save(PositionInTime persisted);

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

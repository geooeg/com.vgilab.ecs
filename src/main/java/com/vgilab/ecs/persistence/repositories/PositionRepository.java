package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.Position;
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
public interface PositionRepository extends PagingAndSortingRepository<Position, Long>, QueryDslPredicateExecutor<Position>  {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Position findOne(Long id);

    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<Position> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<Position> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<Position> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public Position save(Position persisted);

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

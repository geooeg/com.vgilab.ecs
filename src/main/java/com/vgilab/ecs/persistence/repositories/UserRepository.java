package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author smuellner
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String>, QueryDslPredicateExecutor<UserEntity>  {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public UserEntity findOne(String id);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<UserEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<UserEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<UserEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public UserEntity save(UserEntity persisted);

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
package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.AuthorizationCodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author smuellner
 */
public interface AuthorizationCodeRepository extends PagingAndSortingRepository<AuthorizationCodeEntity, String>  {
   
    /**
     *
     * @param id
     * @return
     */
    @Override
    public AuthorizationCodeEntity findOne(String id);
    
    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<AuthorizationCodeEntity> findAll(Pageable pgbl);

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public AuthorizationCodeEntity save(AuthorizationCodeEntity persisted);

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

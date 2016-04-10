package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.entity.DeviceEntity;
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
public interface DeviceRepository extends PagingAndSortingRepository<DeviceEntity, String>, QueryDslPredicateExecutor<DeviceEntity>  {
   
    /**
     *
     * @param id
     * @return
     */
    @Override
    public DeviceEntity findOne(String id);
    
    
    /**
     *
     * @param maker
     * @param identifierForVendor
     * @return
     */
    public DeviceEntity findByMakerAndIdentifierForVendor(String maker, String identifierForVendor);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<DeviceEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<DeviceEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<DeviceEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public DeviceEntity save(DeviceEntity persisted);

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

package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.dto.MediaDto;
import com.vgilab.ecs.persistence.entity.MediaEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author smuellner
 */
public interface MediaRepository  extends PagingAndSortingRepository<MediaEntity, String>, QueryDslPredicateExecutor<MediaEntity>  {

    @Query("SELECT new com.vgilab.ecs.persistence.dto.MediaDto(m.id, m.position.longitude, m.position.latitude, m.event, m.artist, m.title) FROM MediaEntity m WHERE m.trip = :trip AND m.position IS NOT NULL ORDER BY m.trackedOn ASC")
    public List<MediaDto> findByTripAsMediaDto(@Param("trip") final TripEntity trip);
    
    /**
     *
     * @param uuid
     * @return
     */
    @Override
    public MediaEntity findOne(String uuid);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<MediaEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<MediaEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<MediaEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public MediaEntity save(MediaEntity persisted);

    /**
     *
     * @param uuid
     * @return
     */
    @Override
    public boolean exists(String uuid);

    /**
     *
     * @return
     */
    @Override
    public long count();
}
package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.dto.MoodDto;
import com.vgilab.ecs.persistence.entity.MoodEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author smuellner
 */
@Repository
public interface MoodRepository extends PagingAndSortingRepository<MoodEntity, String>, QueryDslPredicateExecutor<MoodEntity>  {

    @Query("SELECT new com.vgilab.ecs.persistence.dto.MoodDto(m.id, m.position.longitude, m.position.latitude, m.emoticon) FROM MoodEntity m WHERE m.trip = :trip AND m.position IS NOT NULL ORDER BY m.trackedOn ASC")
    public List<MoodDto> findByTripAsMoodDto(@Param("trip") final TripEntity trip);
    
    /**
     *
     * @param uuid
     * @return
     */
    @Override
    public MoodEntity findOne(String uuid);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<MoodEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<MoodEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<MoodEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public MoodEntity save(MoodEntity persisted);

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

package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.dto.TagDto;
import com.vgilab.ecs.persistence.entity.MoodEntity;
import com.vgilab.ecs.persistence.entity.TagEntity;
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
public interface TagRepository  extends PagingAndSortingRepository<TagEntity, String>, QueryDslPredicateExecutor<MoodEntity>  {

    @Query("SELECT new com.vgilab.ecs.persistence.dto.TagDto(te.uuid, te.position.longitude, te.position.latitude, te.category) FROM TagEntity te WHERE te.trip = :trip AND te.position IS NOT NULL ORDER BY te.trackedOn ASC")
    public List<TagDto> findByTripAsTagDto(@Param("trip") final TripEntity trip);
    
    /**
     *
     * @param uuid
     * @return
     */
    @Override
    public TagEntity findOne(String uuid);
    
    /**
     *
     * @param sort
     * @return
     */
    @Override
    public Iterable<TagEntity> findAll(Sort sort);

    /**
     *
     * @param pgbl
     * @return
     */
    @Override
    public Page<TagEntity> findAll(Pageable pgbl);

    /**
     *
     * @return
     */
    @Override
    public List<TagEntity> findAll();

    /**
     *
     * @param persisted
     * @return
     */
    @Override
    public TagEntity save(TagEntity persisted);

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

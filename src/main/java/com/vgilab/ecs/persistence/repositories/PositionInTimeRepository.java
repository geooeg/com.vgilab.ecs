package com.vgilab.ecs.persistence.repositories;

import com.vgilab.ecs.persistence.dto.AltitudeInTimeDto;
import com.vgilab.ecs.persistence.dto.PositionInTimeDto;
import com.vgilab.ecs.persistence.dto.SpeedInTimeDto;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
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
 * @author ljzhang
 */
@Repository
public interface PositionInTimeRepository extends PagingAndSortingRepository<PositionInTimeEntity, Long>, QueryDslPredicateExecutor<PositionInTimeEntity>  {

    @Query("SELECT new com.vgilab.ecs.persistence.dto.PositionInTimeDto(p.id, p.position.longitude, p.position.latitude) FROM PositionInTimeEntity p WHERE p.trip = :trip AND p.position IS NOT NULL ORDER BY p.trackedOn ASC")
    public List<PositionInTimeDto> findByTripAsPositionInTimeDto(@Param("trip") final TripEntity trip);
    
    @Query("SELECT new com.vgilab.ecs.persistence.dto.AltitudeInTimeDto(p.id, p.altitude) FROM PositionInTimeEntity p WHERE p.trip = :trip AND p.position IS NOT NULL ORDER BY p.trackedOn ASC")
    public List<AltitudeInTimeDto> findByTripAsAltitudeInTimeDto(@Param("trip") final TripEntity trip);
    
    @Query("SELECT new com.vgilab.ecs.persistence.dto.SpeedInTimeDto(p.id, p.speed) FROM PositionInTimeEntity p WHERE p.trip = :trip AND p.position IS NOT NULL ORDER BY p.trackedOn ASC")
    public List<SpeedInTimeDto> findByTripAsSpeedInTimeDto(@Param("trip") final TripEntity trip);
    
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

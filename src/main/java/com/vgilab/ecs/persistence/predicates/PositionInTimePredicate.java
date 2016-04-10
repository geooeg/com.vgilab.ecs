package com.vgilab.ecs.persistence.predicates;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.entity.QPositionEntity;
import com.vgilab.ecs.persistence.entity.QPositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author smuellner
 */
public class PositionInTimePredicate {
    
    /**
     *
     * @param tripEntity
     * @param latitude
     * @param longitude
     * @param altitude
     * @return
     */
    public static Predicate predicateWithLocationAndDevice(TripEntity tripEntity, String latitude, String longitude, String altitude) {
        final BooleanBuilder where = new BooleanBuilder();
        if (StringUtils.isNotBlank(latitude)) {
            where.and(latitudeContains(Double.valueOf(latitude)));
        }
        if (StringUtils.isNotBlank(longitude)) {
            where.and(longitudeContains(Double.valueOf(longitude)));
        }
        if (StringUtils.isNotBlank(altitude)) {
            where.and(altitudeContains(Double.valueOf(altitude)));
        }
        where.and(QPositionInTimeEntity.positionInTimeEntity.position.latitude.isNotNull());
        where.and(QPositionInTimeEntity.positionInTimeEntity.position.longitude.isNotNull());
        where.and(QPositionInTimeEntity.positionInTimeEntity.altitude.isNotNull());
        where.and(QPositionInTimeEntity.positionInTimeEntity.trip.eq(tripEntity));
        return where;
    }
    
    
    public static Predicate predicate(TripEntity tripEntity) {
        final BooleanBuilder where = new BooleanBuilder();
        where.and(QPositionInTimeEntity.positionInTimeEntity.position.latitude.isNotNull());
        where.and(QPositionInTimeEntity.positionInTimeEntity.position.longitude.isNotNull());
        where.and(QPositionInTimeEntity.positionInTimeEntity.trip.eq(tripEntity));
        return where;
    }
    
    /**
     *
     * @param latitude
     * @return
     */
    public static Predicate latitudeContains(Double latitude) {
        final QPositionEntity position = QPositionInTimeEntity.positionInTimeEntity.position;
        return position.latitude.eq(latitude);
    }
    
    /**
     *
     * @param longitude
     * @return
     */
    public static Predicate longitudeContains(Double longitude) {
        final QPositionEntity position = QPositionInTimeEntity.positionInTimeEntity.position;
        return position.longitude.eq(longitude);
    }
    
    /**
     *
     * @param altitude
     * @return
     */
    public static Predicate altitudeContains(Double altitude) {
        final QPositionInTimeEntity position = QPositionInTimeEntity.positionInTimeEntity;
        return position.altitude.eq(altitude);
    }

}

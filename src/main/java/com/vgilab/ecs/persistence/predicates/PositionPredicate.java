package com.vgilab.ecs.persistence.predicates;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.entity.QPositionEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * Predicate for creating coordinate based SQL filter criterias.
 * 
 * @author ljzhang
 */
public class PositionPredicate {

    /**
     *
     * @param latitude
     * @param longitude
     * @param averageAltitude
     * @return
     */
    public static Predicate predicateWithLocationAndDevice(String latitude, String longitude, String averageAltitude) {
        final BooleanBuilder where = new BooleanBuilder();
        if (StringUtils.isNotBlank(latitude)) {
            where.and(latitudeContains(Double.valueOf(latitude)));
        }
        if (StringUtils.isNotBlank(longitude)) {
            where.and(longitudeContains(Double.valueOf(longitude)));
        }
        if (StringUtils.isNotBlank(averageAltitude)) {
            where.and(averageAltitudeContains(Double.valueOf(averageAltitude)));
        }
        where.and(QPositionEntity.positionEntity.latitude.isNotNull());
        where.and(QPositionEntity.positionEntity.longitude.isNotNull());
        where.and(QPositionEntity.positionEntity.averageAltitude.isNotNull());
        return where;
    }
    
    
    public static Predicate predicate() {
        final BooleanBuilder where = new BooleanBuilder();
        where.and(QPositionEntity.positionEntity.latitude.isNotNull());
        where.and(QPositionEntity.positionEntity.longitude.isNotNull());
        return where;
    }
    
    /**
     *
     * @param latitude
     * @return
     */
    public static Predicate latitudeContains(Double latitude) {
        final QPositionEntity position = QPositionEntity.positionEntity;
        return position.latitude.eq(latitude);
    }
    
    /**
     *
     * @param longitude
     * @return
     */
    public static Predicate longitudeContains(Double longitude) {
        final QPositionEntity position = QPositionEntity.positionEntity;
        return position.longitude.eq(longitude);
    }
    
    /**
     *
     * @param averageAltitude
     * @return
     */
    public static Predicate averageAltitudeContains(Double averageAltitude) {
        final QPositionEntity position = QPositionEntity.positionEntity;
        return position.averageAltitude.eq(averageAltitude);
    }

}

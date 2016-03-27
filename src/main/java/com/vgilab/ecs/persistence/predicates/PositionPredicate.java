package com.vgilab.ecs.persistence.predicates;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.entity.QPosition;
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
        where.and(QPosition.position.latitude.isNotNull());
        where.and(QPosition.position.longitude.isNotNull());
        where.and(QPosition.position.averageAltitude.isNotNull());
        return where;
    }
    
    
    public static Predicate predicate() {
        final BooleanBuilder where = new BooleanBuilder();
        where.and(QPosition.position.latitude.isNotNull());
        where.and(QPosition.position.longitude.isNotNull());
        return where;
    }
    
    /**
     *
     * @param latitude
     * @return
     */
    public static Predicate latitudeContains(Double latitude) {
        final QPosition position = QPosition.position;
        return position.latitude.eq(latitude);
    }
    
    /**
     *
     * @param longitude
     * @return
     */
    public static Predicate longitudeContains(Double longitude) {
        final QPosition position = QPosition.position;
        return position.longitude.eq(longitude);
    }
    
    /**
     *
     * @param averageAltitude
     * @return
     */
    public static Predicate averageAltitudeContains(Double averageAltitude) {
        final QPosition position = QPosition.position;
        return position.averageAltitude.eq(averageAltitude);
    }

}

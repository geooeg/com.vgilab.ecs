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
     * @param altitude
     * @param maker
     * @param model
     * @return
     */
    public static Predicate predicateWithLocationAndDevice(String latitude, String longitude, String altitude, String maker, String model) {
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
        if (StringUtils.isNotBlank(maker)) {
            where.and(makerContains(maker));
        }
        if (StringUtils.isNotBlank(model)) {
            where.and(modelContains(model));
        }
        where.and(QPosition.position.latitude.isNotNull());
        where.and(QPosition.position.longitude.isNotNull());
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
     * @param altitude
     * @return
     */
    public static Predicate altitudeContains(Double altitude) {
        final QPosition position = QPosition.position;
        return position.altitude.eq(altitude);
    }
    
    /**
     *
     * @param maker
     * @return
     */
    public static Predicate makerContains(String maker) {
        final QPosition position = QPosition.position;
        return position.maker.containsIgnoreCase(maker);
    }
    
    /**
     *
     * @param model
     * @return
     */
    public static Predicate modelContains(String model) {
        final QPosition position = QPosition.position;
        return position.model.containsIgnoreCase(model);
    }

}

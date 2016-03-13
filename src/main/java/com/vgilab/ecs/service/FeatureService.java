package com.vgilab.ecs.service;

import com.vgilab.ecs.persistence.entity.Position;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import java.util.ArrayList;
import java.util.List;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * http://docs.geotools.org/stable/tutorials/feature/csv2shp.html
 * 
 * @author ljzhang
 */
@Service
public class FeatureService {

    @Autowired
    private PositionRepository positionRepository;

    public List<SimpleFeature> exportAllFeatures() {
        /*
         * A list to collect features as we create them.
         */
        final List<SimpleFeature> features = new ArrayList<>();
        /*
         * GeometryFactory will be used to create the geometry attribute of each feature,
         * using a Position object for the location.
         */
        final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        final SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(this.getFeatureType());
        final List<Position> allPositions = this.positionRepository.findAll();
        for (final Position curPosition : allPositions) {
            if (null != curPosition.getLatitude() && null != curPosition.getLongitude()) {
                final Double longitude = curPosition.getLongitude();
                final Double latitude = curPosition.getLatitude();
                final Coordinate coordinate = curPosition.getAltitude() != null ? new Coordinate(longitude, latitude, curPosition.getAltitude()) : new Coordinate(longitude, latitude);
                final Point point = geometryFactory.createPoint(coordinate);
                featureBuilder.add(point);
                if (null != curPosition.getAltitude()) {
                    featureBuilder.add(curPosition.getAltitude());
                }
                features.add(featureBuilder.buildFeature(null));
            }
        }
        return features;
    }

    public SimpleFeatureType getFeatureType() {
        final SimpleFeatureTypeBuilder featureTypeBuilder = new SimpleFeatureTypeBuilder();
        featureTypeBuilder.setName("Point");
        featureTypeBuilder.setCRS(DefaultGeographicCRS.WGS84_3D); // set crs first
        featureTypeBuilder.add("the_geom", Point.class); // then add geometry
        featureTypeBuilder.add("height", Double.class);
        return featureTypeBuilder.buildFeatureType();
    }
}

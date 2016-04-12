package com.vgilab.ecs.view;

import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.dto.PositionInTimeDto;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.predicates.PositionInTimePredicate;
import com.vgilab.ecs.persistence.repositories.PositionInTimeRepository;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author smuellner
 */
@Component
@ManagedBean(name = "tripView")
@SessionScoped
public class TripView implements Serializable {

    @Autowired
    private PositionInTimeRepository positionInTimeRepository;

    private LazyDataModel<PositionInTimeEntity> positionsInTime;

    private TripEntity trip;

    private PositionInTimeEntity selected;

    private MapModel tripModel;

    private String selectedImageURL;

    public void preRenderView() {
        this.tripModel = null;
    }

    @PostConstruct
    public void init() {
        this.positionsInTime = new LazyDataModel<PositionInTimeEntity>() {
            @Override
            public List<PositionInTimeEntity> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                final boolean ascending = (null != sortOrder ? SortOrder.ASCENDING.equals(sortOrder) : null);
                final int currentPage = startingAt / maxPerPage;
                final Page<PositionInTimeEntity> page;
                final PageRequest pageRequest;
                if (StringUtils.isNotBlank(sortField) && null != sortOrder) {
                    pageRequest = new PageRequest(currentPage, maxPerPage, new Sort(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
                } else {
                    pageRequest = new PageRequest(currentPage, maxPerPage, new Sort(Sort.Direction.DESC, "trackedOn"));
                }
                if (null != filters && !filters.isEmpty()) {
                    page = TripView.this.positionInTimeRepository.findAll(TripView.this.getPositionPredicate(filters), pageRequest);
                    TripView.this.positionsInTime.setRowCount(Long.valueOf(TripView.this.positionInTimeRepository.count(TripView.this.getPositionPredicate(filters))).intValue());
                } else {
                    page = TripView.this.positionInTimeRepository.findAll(PositionInTimePredicate.predicate(TripView.this.trip), pageRequest);
                    TripView.this.positionsInTime.setRowCount(Long.valueOf(TripView.this.positionInTimeRepository.count(PositionInTimePredicate.predicate(TripView.this.trip))).intValue());
                }
                final List<PositionInTimeEntity> result = new LinkedList<>();
                for (PositionInTimeEntity curCoordinate : page) {
                    result.add(curCoordinate);
                }
                return result;
            }

            @Override
            public PositionInTimeEntity getRowData(String rowKey) {
                return TripView.this.positionInTimeRepository.findOne(Long.valueOf(rowKey));
            }

            @Override
            public Object getRowKey(PositionInTimeEntity positionInTimeEntity) {
                return positionInTimeEntity.getId();
            }
        };
    }

    private Predicate getPositionPredicate(Map<String, Object> filters) {
        final String latitude = filters.containsKey("latitude") ? String.valueOf(filters.get("latitude")) : null;
        final String longitude = filters.containsKey("longitude") ? String.valueOf(filters.get("longitude")) : null;
        final String averageAltitude = filters.containsKey("altitude") ? String.valueOf(filters.get("altitude")) : null;
        return PositionInTimePredicate.predicateWithLocationAndDevice(this.trip, latitude, longitude, averageAltitude);
    }

    public void onRowSelect(SelectEvent event) {
        this.selected = (PositionInTimeEntity) event.getObject();
        this.selectedImageURL = null;
        this.tripModel.getMarkers().clear();
        if (null != this.selected && null != this.selected.getPosition().getLatitude() && null != this.selected.getPosition().getLongitude()) {
            final LatLng coord = new LatLng(this.selected.getPosition().getLatitude(), this.selected.getPosition().getLongitude());
            final StringBuilder altitude = new StringBuilder();
            altitude.append(null == this.selected.getAltitude() ? "-" : this.selected.getAltitude());
            this.tripModel.addOverlay(new Marker(coord, altitude.toString(), "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        }
    }

    public void onRowUnselect(SelectEvent event) {
        this.selected = null;
    }

    /**
     *
     * @return
     */
    public LazyDataModel<PositionInTimeEntity> getPositionsInTime() {
        return this.positionsInTime;
    }

    /**
     * @return the selected
     */
    public PositionInTimeEntity getSelected() {
        return selected;
    }


    /**
     * @return the center position
     */
    public String getCenter() {
        return (null != this.selected && null != this.selected.getPosition().getLatitude() && null != this.selected.getPosition().getLongitude()) ? this.selected.getPosition().getLatitude().toString() + "," + this.selected.getPosition().getLongitude().toString() : "0.0,0.0";
    }

    /**
     * @return the markerModel
     */
    public MapModel getTripModel() {
        if (this.tripModel == null && this.trip != null) {
            this.tripModel = new DefaultMapModel();
            final List<PositionInTimeDto> positions = this.positionInTimeRepository.findByTripAsPositionInTimeDto(this.trip);
            final Polyline tripPolyline = new Polyline();
            tripPolyline.setStrokeWeight(1);
            tripPolyline.setStrokeColor("blue");
            tripPolyline.setStrokeOpacity(0.8);
            positions.stream().map((curPositionInTimeEntity) -> new LatLng(curPositionInTimeEntity.getLatitude(), curPositionInTimeEntity.getLongitude())).forEach((latLng) -> {
                tripPolyline.getPaths().add(latLng);
            });
            this.tripModel.addOverlay(tripPolyline);
        }
        return this.tripModel;
    }

    /**
     * @return the selectedImageURL
     */
    public String getSelectedImageURL() {
        return selectedImageURL;
    }

    /**
     * @return the trip
     */
    public TripEntity getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }
}

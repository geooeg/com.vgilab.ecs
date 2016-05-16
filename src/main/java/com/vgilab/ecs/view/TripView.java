package com.vgilab.ecs.view;

import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.dto.AltitudeInTimeDto;
import com.vgilab.ecs.persistence.dto.PositionInTimeDto;
import com.vgilab.ecs.persistence.dto.SpeedInTimeDto;
import com.vgilab.ecs.persistence.entity.PositionInTimeEntity;
import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.predicates.PositionInTimePredicate;
import com.vgilab.ecs.persistence.repositories.PositionInTimeRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
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
    private TripRepository tripRepository;

    @Autowired
    private PositionInTimeRepository positionInTimeRepository;

    private LazyDataModel<PositionInTimeEntity> positionsInTime;

    private TripEntity trip;

    private PositionInTimeEntity selected;

    private MapModel tripModel;

    private String selectedImageURL;

    private List<AltitudeInTimeDto> altitudeOverTime;

    private List<SpeedInTimeDto> speedOverTime;

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
        this.updateMarkers();
    }

    public void onRowUnselect(SelectEvent event) {
        this.selected = null;
    }

    public void remove() {
        if (trip != null) {
            trip = tripRepository.findOne(trip.getId());
            positionInTimeRepository.delete(trip.getPositionsInTime());
            trip.setPositionsInTime(null);
            trip = tripRepository.save(trip);
            tripRepository.delete(trip);
            final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            configurableNavigationHandler.performNavigation("/?faces-redirect=true");
        }
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
     *
     * @return
     */
    public String deleteTrip() {
        this.positionInTimeRepository.delete(this.trip.getPositionsInTime());
        this.trip.getPositionsInTime().clear();
        this.trip = this.tripRepository.save(this.trip);
        this.tripRepository.delete(this.trip);
        return "index.xhtml?faces-redirect=true";
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
            this.updateMarkers();
        }
        return this.tripModel;
    }

    /**
     * @return the markerModel
     */
    public LineChartModel getAltitudeLineModel() {
        this.altitudeOverTime = this.positionInTimeRepository.findByTripAsAltitudeInTimeDto(this.trip);
        final LineChartModel model = new LineChartModel();
        model.setTitle("Altitude in meter");
        model.setLegendPosition("e");
        final Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMax(altitudeOverTime.size());
        final LineChartSeries series = new LineChartSeries();
        series.setShowMarker(false);
        series.setLabel("");
        IntStream.range(0, altitudeOverTime.size())
                .forEach(idx -> {
                    series.set(idx, altitudeOverTime.get(idx).getAltitude());
                });
        model.addSeries(series);
        return model;
    }

    public LineChartModel getSpeedLineModel() {
        this.speedOverTime = this.positionInTimeRepository.findByTripAsSpeedInTimeDto(this.trip);
        final LineChartModel model = new LineChartModel();
        model.setTitle("Speed in meter per seconds");
        model.setLegendPosition("e");
        final Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMax(this.speedOverTime.size());
        final LineChartSeries series = new LineChartSeries();
        series.setShowMarker(false);
        series.setLabel("");
        IntStream.range(0, this.speedOverTime.size())
                .forEach(idx -> {
                    series.set(idx, this.speedOverTime.get(idx).getSpeed());
                });
        model.addSeries(series);
        return model;
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

    public void speedSelect(ItemSelectEvent event) {
        final SpeedInTimeDto speedSelected = this.speedOverTime.get(event.getItemIndex());
        this.selected = this.positionInTimeRepository.findOne(speedSelected.getId());
        this.updateMarkers();
    }

    public void altitudeSelect(ItemSelectEvent event) {
        final AltitudeInTimeDto altitudeSelected = this.altitudeOverTime.get(event.getItemIndex());
        this.selected = this.positionInTimeRepository.findOne(altitudeSelected.getId());
        this.updateMarkers();
    }

    private void updateMarkers() {
        if (null != this.selected && null != this.selected.getPosition().getLatitude() && null != this.selected.getPosition().getLongitude()) {
            final LatLng coord = new LatLng(this.selected.getPosition().getLatitude(), this.selected.getPosition().getLongitude());
            final StringBuilder altitude = new StringBuilder();
            altitude.append(null == this.selected.getAltitude() ? "-" : this.selected.getAltitude());
            this.tripModel.addOverlay(new Marker(coord, altitude.toString(), "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        }
        this.trip.getMoods().stream().forEach(m -> {
            final LatLng coord = new LatLng(m.getPosition().getLatitude(), m.getPosition().getLongitude());
            this.tripModel.addOverlay(new Marker(coord, m.getEmoticon().toString()));
        });
        this.trip.getTags().stream().forEach(t -> {
            final LatLng coord = new LatLng(t.getPosition().getLatitude(), t.getPosition().getLongitude());
            this.tripModel.addOverlay(new Marker(coord, t.getContent()));
        });
    }
}

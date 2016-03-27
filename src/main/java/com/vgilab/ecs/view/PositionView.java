package com.vgilab.ecs.view;

import com.mysema.query.types.Predicate;
import com.vgilab.ecs.persistence.entity.Position;
import com.vgilab.ecs.persistence.predicates.PositionPredicate;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import com.vgilab.ecs.service.ShapefileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author ljzhang
 */
@Component
@ManagedBean(name = "positionView")
@SessionScoped
public class PositionView implements Serializable {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ShapefileService shapefileService;

    private LazyDataModel<Position> positions;

    private Position selected;

    private MapModel markerModel;

    private String selectedImageURL;
   
    private StreamedContent shapefilesWithAllFeatures;
    
    private StreamedContent shapefilesWithAverageAltitude;
    
    @PostConstruct
    public void init() {
        this.markerModel = new DefaultMapModel();
        this.positions = new LazyDataModel<Position>() {
            @Override
            public List<Position> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                final boolean ascending = (null != sortOrder ? SortOrder.ASCENDING.equals(sortOrder) : null);
                final int currentPage = startingAt / maxPerPage;
                final Page<Position> page;
                final PageRequest pageRequest;
                if (StringUtils.isNotBlank(sortField) && null != sortOrder) {
                    pageRequest = new PageRequest(currentPage, maxPerPage, new Sort(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
                } else {
                    pageRequest = new PageRequest(currentPage, maxPerPage);
                }
                if (null != filters && !filters.isEmpty()) {
                    page = PositionView.this.positionRepository.findAll(PositionView.this.getCoordinatePredicate(filters), pageRequest);
                    PositionView.this.positions.setRowCount(Long.valueOf(PositionView.this.positionRepository.count(PositionView.this.getCoordinatePredicate(filters))).intValue());
                } else {
                    page = PositionView.this.positionRepository.findAll(PositionPredicate.predicate(), pageRequest);
                    PositionView.this.positions.setRowCount(Long.valueOf(PositionView.this.positionRepository.count(PositionPredicate.predicate())).intValue());
                }
                final List<Position> result = new LinkedList<>();
                for (Position curCoordinate : page) {
                    result.add(curCoordinate);
                }
                return result;
            }

            @Override
            public Position getRowData(String rowKey) {
                return PositionView.this.positionRepository.findOne(Long.valueOf(rowKey));
            }

            @Override
            public Object getRowKey(Position product) {
                return product.getId();
            }
        };
    }

    private Predicate getCoordinatePredicate(Map<String, Object> filters) {
        final String latitude = filters.containsKey("latitude") ? String.valueOf(filters.get("latitude")) : null;
        final String longitude = filters.containsKey("longitude") ? String.valueOf(filters.get("longitude")) : null;
        final String averageAltitude = filters.containsKey("averageAltitude") ? String.valueOf(filters.get("s")) : null;
        return PositionPredicate.predicateWithLocationAndDevice(latitude, longitude, averageAltitude);
    }

    public void onRowSelect(SelectEvent event) {
        this.selected = (Position) event.getObject();
        this.selectedImageURL = null;
        this.getMarkerModel().getMarkers().clear();
        if (null != this.selected && null != this.selected.getLatitude() && null != this.selected.getLongitude()) {
            final LatLng coord = new LatLng(this.selected.getLatitude(), this.selected.getLongitude());
            final StringBuilder altitude = new StringBuilder();
            altitude.append(null == this.selected.getAverageAltitude() ? "-" : this.selected.getAverageAltitude());
            this.getMarkerModel().addOverlay(new Marker(coord, altitude.toString(), "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        }
    }

    public void onRowUnselect(SelectEvent event) {
        this.selected = null;
    }

    public Position prepareCreate() {
        this.selected = new Position();
        return getSelected();
    }

    public void update() {
        if (this.selected != null) {
            this.positionRepository.save(this.selected);
        }
    }

    /**
     *
     * @return
     */
    public LazyDataModel<Position> getPositions() {
        return this.positions;
    }

    /**
     * @return the selected
     */
    public Position getSelected() {
        return selected;
    }

    /**
     * @return the center position
     */
    public String getCenter() {
        return (null != this.selected && null != this.selected.getLatitude() && null != this.selected.getLongitude()) ? this.selected.getLatitude().toString() + "," + this.selected.getLongitude().toString() : "0.0,0.0";
    }

    /**
     * @return the markerModel
     */
    public MapModel getMarkerModel() {
        return this.markerModel;
    }

    /**
     * @return the selectedImageURL
     */
    public String getSelectedImageURL() {
        return selectedImageURL;
    }

    public StreamedContent getShapefileWithAverageAltitude() {
        try {
            final File zippedShapefiles = this.shapefileService.exportShapefileWithAllFeaturesAndAverageAltitude();
            this.shapefilesWithAverageAltitude = new DefaultStreamedContent(new FileInputStream(zippedShapefiles), "application/zip", this.shapefileService.getArchiveFilenameAverageAltitude());
        } catch (FileNotFoundException ex) {
            FacesMessage message = new FacesMessage("Failed", "Could not export shapefiles with average altitude.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(PositionView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.shapefilesWithAverageAltitude;
    }
    
    public StreamedContent getShapefilesWithAllFeatures() {
        try {
            final File zippedShapefiles = this.shapefileService.exportShapefileWithAllFeatures();
            this.shapefilesWithAllFeatures = new DefaultStreamedContent(new FileInputStream(zippedShapefiles), "application/zip", this.shapefileService.getArchiveFilenameAll());
        } catch (FileNotFoundException ex) {
            FacesMessage message = new FacesMessage("Failed", "Could not export shapefiles.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(PositionView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.shapefilesWithAllFeatures;
    }
}

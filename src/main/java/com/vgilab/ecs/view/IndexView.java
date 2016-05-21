package com.vgilab.ecs.view;

import com.vgilab.ecs.persistence.entity.TripEntity;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.service.ShapefileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
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
@ManagedBean(name = "indexView")
@SessionScoped
public class IndexView implements Serializable {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ShapefileService shapefileService;

    private LazyDataModel<TripEntity> trips;
   
    private StreamedContent shapefilesWithAllFeatures;
    
    private StreamedContent shapefilesWithAverageAltitude;
    
    @PostConstruct
    public void init() {
        this.trips = new LazyDataModel<TripEntity>() {
            @Override
            public List<TripEntity> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                final boolean ascending = (null != sortOrder ? SortOrder.ASCENDING.equals(sortOrder) : null);
                final int currentPage = startingAt / maxPerPage;
                final Page<TripEntity> page;
                final PageRequest pageRequest;
                if (StringUtils.isNotBlank(sortField) && null != sortOrder) {
                    pageRequest = new PageRequest(currentPage, maxPerPage, new Sort(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
                } else {
                    pageRequest = new PageRequest(currentPage, maxPerPage, new Sort(Sort.Direction.DESC, "startedOn").and(new Sort(Sort.Direction.DESC, "stoppedOn")));
                }
                page = IndexView.this.tripRepository.findAll(pageRequest);
                IndexView.this.trips.setRowCount(Long.valueOf(IndexView.this.tripRepository.count()).intValue());
                /*
                if (null != filters && !filters.isEmpty()) {
                    page = IndexView.this.tripRepository.findAll(IndexView.this.getCoordinatePredicate(filters), pageRequest);
                    IndexView.this.trips.setRowCount(Long.valueOf(IndexView.this.tripRepository.count(TripView.this.getCoordinatePredicate(filters))).intValue());
                } else {
                    page = IndexView.this.tripRepository.findAll(TripPredicate.predicate(), pageRequest);
                    IndexView.this.trips.setRowCount(Long.valueOf(IndexView.this.tripRepository.count(TripPredicate.predicate())).intValue());
                }
                */
                return page.getContent();
            }

            @Override
            public TripEntity getRowData(String rowKey) {
                return IndexView.this.tripRepository.findOne(rowKey);
            }

            @Override
            public Object getRowKey(TripEntity tripEntity) {
                return tripEntity.getId();
            }
        };
    }

    /*
    private Predicate getTripPredicate(Map<String, Object> filters) {
        final String latitude = filters.containsKey("latitude") ? String.valueOf(filters.get("latitude")) : null;
        final String longitude = filters.containsKey("longitude") ? String.valueOf(filters.get("longitude")) : null;
        final String averageAltitude = filters.containsKey("averageAltitude") ? String.valueOf(filters.get("s")) : null;
        return TripPredicate.predicateWithLocationAndDevice(latitude, longitude, averageAltitude);
    }*/

    public void onRowSelect(SelectEvent event) {
        final TripEntity tripEntity = (TripEntity) event.getObject();
        final TripView productDetailView = (TripView) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "tripView");
        productDetailView.setTripId(tripEntity.getId());
        final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("/trip/overview.xhtml?tripId=" + tripEntity.getId() + "&faces-redirect=true");
    }
    
    /**
     *
     * @return
     */
    public LazyDataModel<TripEntity> getTrips() {
        return this.trips;
    }

    public StreamedContent getShapefileWithAverageAltitude() {
        try {
            final File zippedShapefiles = this.shapefileService.exportShapefileWithAllFeaturesAndAverageAltitude();
            this.shapefilesWithAverageAltitude = new DefaultStreamedContent(new FileInputStream(zippedShapefiles), "application/zip", this.shapefileService.getArchiveFilenameAverageAltitude());
        } catch (FileNotFoundException ex) {
            FacesMessage message = new FacesMessage("Failed", "Could not export shapefiles with average altitude.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.shapefilesWithAllFeatures;
    }
}

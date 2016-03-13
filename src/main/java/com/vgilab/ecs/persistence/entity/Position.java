package com.vgilab.ecs.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ljzhang
 */
@Entity
@Table(name = "positions")
public class Position implements Serializable {
    private static final long serialVersionUID = 12L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "maker")
    private String maker;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "software")
    private String software;
    
    @Column(name = "source")
    private String source;
       
    @Column(name = "longitude")
    private Double longitude;
        
    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "altitude")
    private Double altitude;
    
    @Column(name = "accuracy")
    private String accuracy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = true)
    private Calendar trackedOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Calendar createdOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_on", nullable = true)
    private Calendar modifiedOn;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the maker
     */
    public String getMaker() {
        return maker;
    }

    /**
     * @param maker the maker to set
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the software
     */
    public String getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(String software) {
        this.software = software;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * @return the altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    /**
     * @return the accuracy
     */
    public String getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * @return the trackedOn
     */
    public Calendar getTrackedOn() {
        return trackedOn;
    }

    /**
     * @param trackedOn the trackedOn to set
     */
    public void setTrackedOn(Calendar trackedOn) {
        this.trackedOn = trackedOn;
    }

    /**
     * @return the createdOn
     */
    public Calendar getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the modifiedOn
     */
    public Calendar getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.Coordinate[ id=" + getId() + " ]";
    }

}

package com.vgilab.ecs.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "average_altitude")
    private Double averageAltitude;
    
    @OneToMany(mappedBy="position", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PositionInTime> positionsInTime = new LinkedList<>();

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
     * @return the averageAltitude
     */
    public Double getAverageAltitude() {
        return averageAltitude;
    }

    /**
     * @param averageAltitude the averageAltitude to set
     */
    public void setAverageAltitude(Double averageAltitude) {
        this.averageAltitude = averageAltitude;
    }

    /**
     * @return the positionsInTime
     */
    public List<PositionInTime> getPositionsInTime() {
        return positionsInTime;
    }

    /**
     * @param positionsInTime the positionsInTime to set
     */
    public void setPositionsInTime(List<PositionInTime> positionsInTime) {
        this.positionsInTime = positionsInTime;
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
        return "com.vgilab.ecs.persistence.entity.Position[ id=" + getId() + " ]";
    }

}

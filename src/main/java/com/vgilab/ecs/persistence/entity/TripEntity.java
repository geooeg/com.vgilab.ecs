package com.vgilab.ecs.persistence.entity;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "trips")
public class TripEntity extends UuidEntity {

    private static final long serialVersionUID = 1L;
        
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PositionInTimeEntity> positionsInTime = new LinkedList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startedOn", nullable = false)
    private Calendar startedOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stoppedOn", nullable = true)
    private Calendar stoppedOn;
        
    /**
     * @return the device
     */
    public DeviceEntity getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the positionsInTime
     */
    public List<PositionInTimeEntity> getPositionsInTime() {
        return positionsInTime;
    }

    /**
     * @param positionsInTime the positionsInTime to set
     */
    public void setPositionsInTime(List<PositionInTimeEntity> positionsInTime) {
        this.positionsInTime = positionsInTime;
    }

    /**
     * @return the startedOn
     */
    public Calendar getStartedOn() {
        return startedOn;
    }

    /**
     * @param startedOn the startedOn to set
     */
    public void setStartedOn(Calendar startedOn) {
        this.startedOn = startedOn;
    }

    /**
     * @return the stoppedOn
     */
    public Calendar getStoppedOn() {
        return stoppedOn;
    }

    /**
     * @param stoppedOn the stoppedOn to set
     */
    public void setStoppedOn(Calendar stoppedOn) {
        this.stoppedOn = stoppedOn;
    }
    
    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.TripEntity[ id=" + getId() + " ]";
    }
}

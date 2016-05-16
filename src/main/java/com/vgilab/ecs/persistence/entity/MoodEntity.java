package com.vgilab.ecs.persistence.entity;

import com.vgilab.ecs.enums.Emoticon;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "moods")
public class MoodEntity extends UuidEntity {
    
    @ManyToOne
    @JoinColumn(name = "position_id", nullable=false)
    private PositionEntity position;
    
    @ManyToOne
    @JoinColumn(name = "device_id", nullable=true)
    private DeviceEntity device;
    
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable=true)
    private TripEntity trip;

    @Enumerated(EnumType.STRING)
    @Column(name = "emoticon", length = 30)
    private Emoticon emoticon = Emoticon.HAPPY;
    
    @Column(name = "message")
    private String message;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trackedOn", nullable = true)
    private Calendar trackedOn;
    
    /**
     * @return the position
     */
    public PositionEntity getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(PositionEntity position) {
        this.position = position;
    }

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

    /**
     * @return the emoticon
     */
    public Emoticon getEmoticon() {
        return emoticon;
    }

    /**
     * @param emoticon the emoticon to set
     */
    public void setEmoticon(Emoticon emoticon) {
        this.emoticon = emoticon;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.MoodEntity[ id=" + getId() + " ]";
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

}

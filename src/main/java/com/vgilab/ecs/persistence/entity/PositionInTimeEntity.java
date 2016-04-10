package com.vgilab.ecs.persistence.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ljzhang
 */
@Entity
@Table(name = "positions_in_time")
public class PositionInTimeEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private PositionEntity position;
        
    @Column(name = "altitude")
    private Double altitude;
    
    @Column(name = "altitude_accuracy")
    private Double altitudeAccuracy;
    
    @Column(name = "horizontal_accuracy")
    private Double horizontalAccuracy;
   
    @Column(name = "vertical_accuracy")
    private Double verticalAccuracy;
    
    @Column(name = "direction")
    private Double direction;
    
    @Column(name = "speed")
    private Double speed;
    
    @Column(name = "floor")
    private Integer floor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trackedOn", nullable = true)
    private Calendar trackedOn;
    
    @Override
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
     * @return the altitudeAccuracy
     */
    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    /**
     * @param altitudeAccuracy the altitudeAccuracy to set
     */
    public void setAltitudeAccuracy(Double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    /**
     * @return the horizontalAccuracy
     */
    public Double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    /**
     * @param horizontalAccuracy the horizontalAccuracy to set
     */
    public void setHorizontalAccuracy(Double horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }

    /**
     * @return the verticalAccuracy
     */
    public Double getVerticalAccuracy() {
        return verticalAccuracy;
    }

    /**
     * @param verticalAccuracy the verticalAccuracy to set
     */
    public void setVerticalAccuracy(Double verticalAccuracy) {
        this.verticalAccuracy = verticalAccuracy;
    }

    /**
     * @return the direction
     */
    public Double getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Double direction) {
        this.direction = direction;
    }

    /**
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return the floor
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PositionInTimeEntity)) {
            return false;
        }
        PositionInTimeEntity other = (PositionInTimeEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.PositionInTime[ id=" + id + " ]";
    }
    
}

package com.vgilab.ecs.persistence.entity;

import java.io.Serializable;
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
public class PositionInTime implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    
    @Column(name = "maker")
    private String maker;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "software")
    private String software;
    
    @Column(name = "source")
    private String source;
    
    @Column(name = "altitude")
    private Double altitude;
    
    @Column(name = "horizontal_accuracy")
    private Double horizontalAccuracy;
   
    @Column(name = "vertical_accuracy")
    private Double verticalAccuracy;
    
    @Column(name = "direction")
    private Double direction;
    
    @Column(name = "speed")
    private Double speed;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = true)
    private Calendar trackedOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Calendar createdOn;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PositionInTime)) {
            return false;
        }
        PositionInTime other = (PositionInTime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.PositionInTime[ id=" + id + " ]";
    }
    
}

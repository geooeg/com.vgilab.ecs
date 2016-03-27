package com.vgilab.ecs.rest;

import javax.validation.constraints.NotNull;

/**
 *
 * @author ljzhang
 */
public class PositionResource {
    
    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @NotNull
    private Double altitude;
    
    private Double horizontalAccuracy;
   
    private Double verticalAccuracy;
    
    private Double direction;
    
    private Double speed;

    @NotNull
    private Long trackedOn;


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
    public Long getTrackedOn() {
        return trackedOn;
    }

    /**
     * @param trackedOn the trackedOn to set
     */
    public void setTrackedOn(Long trackedOn) {
        this.trackedOn = trackedOn;
    }
}

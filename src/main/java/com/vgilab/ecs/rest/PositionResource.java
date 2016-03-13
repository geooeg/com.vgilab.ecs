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

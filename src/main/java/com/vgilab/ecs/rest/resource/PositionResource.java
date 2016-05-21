package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ljzhang
 */
@JsonInclude(Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PositionResource {

    @NotNull
    @JsonProperty("x")
    private Double longitude;

    @NotNull
    @JsonProperty("y")
    private Double latitude;

    @NotNull
    @JsonProperty("z")
    private Double altitude;

    @JsonProperty("ha")
    private Double horizontalAccuracy;

    @JsonProperty("va")
    private Double verticalAccuracy;

    @JsonProperty("o")
    private Double direction;

    @JsonProperty("v")
    private Double speed;
    
    @JsonProperty("f")
    private Integer floor;

    @NotNull
    @JsonProperty("t")
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
}

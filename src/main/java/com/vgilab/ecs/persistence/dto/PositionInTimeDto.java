package com.vgilab.ecs.persistence.dto;

/**
 *
 * @author smuellner
 */
public class PositionInTimeDto {
    
    private Double longitude;

    private Double latitude;

    
    public PositionInTimeDto(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
}

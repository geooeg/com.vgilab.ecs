package com.vgilab.ecs.persistence.dto;

/**
 *
 * @author smuellner
 */
public class AltitudeInTimeDto extends BaseDto {
    
    private Double altitude;

    public AltitudeInTimeDto(Long id, Double altitude) {
        super(id);
        this.altitude = altitude;
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
}

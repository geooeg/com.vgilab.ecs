package com.vgilab.ecs.persistence.dto;

/**
 *
 * @author smuellner
 */
public class SpeedInTimeDto extends BaseDto {
    
    private Double speed;

    public SpeedInTimeDto(Long id, Double speed) {
        super(id);
        this.speed = speed;
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
}
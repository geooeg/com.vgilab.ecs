package com.vgilab.ecs.rest.response;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class StopTripResponse {
    
    @NotNull
    private String tripId;
    
    private Long stoppedOn;

    /**
     * @return the tripId
     */
    public String getTripId() {
        return tripId;
    }

    /**
     * @param tripId the tripId to set
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    /**
     * @return the stoppedOn
     */
    public Long getStoppedOn() {
        return stoppedOn;
    }

    /**
     * @param stoppedOn the stoppedOn to set
     */
    public void setStoppedOn(Long stoppedOn) {
        this.stoppedOn = stoppedOn;
    }
}

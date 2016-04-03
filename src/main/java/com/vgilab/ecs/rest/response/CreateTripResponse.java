package com.vgilab.ecs.rest.response;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class CreateTripResponse {
    
    @NotNull
    private String tripId;

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
}

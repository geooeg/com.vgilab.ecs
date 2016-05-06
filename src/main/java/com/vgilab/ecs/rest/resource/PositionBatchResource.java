package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ljzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PositionBatchResource {

    @NotNull
    private String tripId;

    private List<PositionResource> positions;

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
     * @return the positions
     */
    public List<PositionResource> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<PositionResource> positions) {
        this.positions = positions;
    }
    
}

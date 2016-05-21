package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author smuellner
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class StopTripResource {
    @NotNull
    private String tripId;
    
    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime endTime;
    
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
     * @return the endTime
     */
    public DateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }
        
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}

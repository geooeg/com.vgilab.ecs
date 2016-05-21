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
public class CreateTripResource {
    
    @NotNull
    private String deviceId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime startTime;
    
    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the startTime
     */
    public DateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }
}

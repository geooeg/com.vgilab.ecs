package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Calendar;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author smuellner
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class CreateMoodResource {
    
    @NotNull
    private String deviceId;
    
    private String tripId;
    
    private PositionResource position;
    
    @NotNull
    private String emoticon;
    
    private String message;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Calendar trackedOn;

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
     * @return the position
     */
    public PositionResource getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(PositionResource position) {
        this.position = position;
    }

    /**
     * @return the emoticon
     */
    public String getEmoticon() {
        return emoticon;
    }

    /**
     * @param emoticon the emoticon to set
     */
    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the trackedOn
     */
    public Calendar getTrackedOn() {
        return trackedOn;
    }

    /**
     * @param trackedOn the trackedOn to set
     */
    public void setTrackedOn(Calendar trackedOn) {
        this.trackedOn = trackedOn;
    }
    
}

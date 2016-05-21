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
public class CreateTagResource {
    
    @NotNull
    private String deviceId;
    
    private String tripId;
    
    private PositionResource position;
    
    @NotNull
    private String contentType;
    
    private String category;

    private String title;

    @NotNull
    private String content;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime trackedOn;

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
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the trackedOn
     */
    public DateTime getTrackedOn() {
        return trackedOn;
    }

    /**
     * @param trackedOn the trackedOn to set
     */
    public void setTrackedOn(DateTime trackedOn) {
        this.trackedOn = trackedOn;
    }

}

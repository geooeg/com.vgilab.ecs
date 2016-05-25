package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author smuellner
 */
@JsonInclude(Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMediaResource {
    
    @NotNull
    private String deviceId;
    
    private String tripId;
    
    private PositionResource position;
    
    @NotNull
    private String event;
    
    private String title;
    
    private String artist;
    
    private String album;
    
    private String genre;
    
    private Double playbackDuration;
    
    private Double currentPlaybackTime;
    
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
     * @return the event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(String event) {
        this.event = event;
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
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }
    
    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the playbackDuration
     */
    public Double getPlaybackDuration() {
        return playbackDuration;
    }

    /**
     * @param playbackDuration the playbackDuration to set
     */
    public void setPlaybackDuration(Double playbackDuration) {
        this.playbackDuration = playbackDuration;
    }

    /**
     * @return the currentPlaybackTime
     */
    public Double getCurrentPlaybackTime() {
        return currentPlaybackTime;
    }

    /**
     * @param currentPlaybackTime the currentPlaybackTime to set
     */
    public void setCurrentPlaybackTime(Double currentPlaybackTime) {
        this.currentPlaybackTime = currentPlaybackTime;
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

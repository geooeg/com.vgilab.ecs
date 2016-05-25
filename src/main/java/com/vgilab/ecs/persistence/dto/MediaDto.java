package com.vgilab.ecs.persistence.dto;

import com.vgilab.ecs.enums.MediaEvent;

/**
 *
 * @author smuellner
 */
public class MediaDto extends UuidDto {

    private Double longitude;

    private Double latitude;

    private MediaEvent mediaEvent;
    
    private String artist;
    
    private String title;

    public MediaDto(String uuid, Double longitude, Double latitude, MediaEvent mediaEvent, String artist, String title) {
        super(uuid);
        this.longitude = longitude;
        this.latitude = latitude;
        this.mediaEvent = mediaEvent;
        this.artist = artist;
        this.title = title;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the mediaEvent
     */
    public MediaEvent getMediaEvent() {
        return mediaEvent;
    }

    /**
     * @param mediaEvent the mediaEvent to set
     */
    public void setMediaEvent(MediaEvent mediaEvent) {
        this.mediaEvent = mediaEvent;
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

package com.vgilab.ecs.persistence.entity;

import com.vgilab.ecs.enums.MediaEvent;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "medias")
public class MediaEntity extends UuidEntity {
    
    @ManyToOne
    @JoinColumn(name = "position_id", nullable=false)
    private PositionEntity position;
    
    @ManyToOne
    @JoinColumn(name = "device_id", nullable=true)
    private DeviceEntity device;
    
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable=true)
    private TripEntity trip;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", length = 30)
    private MediaEvent event = MediaEvent.UNKOWN;
    
    @Column(name = "artist")
    private String artist;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name = "album")
    private String album;
        
    @Column(name = "playback_duration")
    private Double playbackDuration;
    
    @Column(name = "current_playback_time")
    private Double currentPlaybackTime;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tracked_on", nullable = true)
    private Calendar trackedOn;

    /**
     * @return the position
     */
    public PositionEntity getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    /**
     * @return the device
     */
    public DeviceEntity getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    /**
     * @return the trip
     */
    public TripEntity getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(TripEntity trip) {
        this.trip = trip;
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
     * @return the event
     */
    public MediaEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(MediaEvent event) {
        this.event = event;
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
    public Calendar getTrackedOn() {
        return trackedOn;
    }

    /**
     * @param trackedOn the trackedOn to set
     */
    public void setTrackedOn(Calendar trackedOn) {
        this.trackedOn = trackedOn;
    }
        
    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.MediaEntity[ id=" + getId() + " ]";
    }
}

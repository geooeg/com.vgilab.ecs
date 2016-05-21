package com.vgilab.ecs.persistence.entity;

import com.vgilab.ecs.enums.ContentType;
import com.vgilab.ecs.enums.TagCategory;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "tags")
public class TagEntity extends UuidEntity {
    
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
    @Column(name = "content_type", nullable=false, length=30)
    private ContentType contentType = ContentType.QR_CODE;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable=false, length=30)
    private TagCategory category = TagCategory.GENERAL;

    @Column(name = "title", nullable=true)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;
    
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
     * @return the contentType
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
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
     * @return the category
     */
    public TagCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(TagCategory category) {
        this.category = category;
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
    
    public boolean isUrl() {
        return ContentType.URL.equals(this.contentType);
    }
}

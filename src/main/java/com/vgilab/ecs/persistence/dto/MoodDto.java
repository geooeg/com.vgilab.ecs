package com.vgilab.ecs.persistence.dto;

import com.vgilab.ecs.enums.Emoticon;

/**
 *
 * @author smuellner
 */
public class MoodDto extends UuidDto {
    
    private Double longitude;

    private Double latitude;
    
    private Emoticon emoticon;

    public MoodDto(String uuid, Double longitude, Double latitude, Emoticon emoticon) {
        super(uuid);
        this.longitude = longitude;
        this.latitude = latitude;
        this.emoticon = emoticon;
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
     * @return the emoticon
     */
    public Emoticon getEmoticon() {
        return emoticon;
    }

    /**
     * @param emoticon the emoticon to set
     */
    public void setEmoticon(Emoticon emoticon) {
        this.emoticon = emoticon;
    }
}

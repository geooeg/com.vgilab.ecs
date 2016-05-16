package com.vgilab.ecs.persistence.dto;

import com.vgilab.ecs.enums.TagCategory;

/**
 *
 * @author smuellner
 */
public class TagDto extends UuidDto {
    
    private Double longitude;

    private Double latitude;
    
    private TagCategory category;

    public TagDto(String uuid, Double longitude, Double latitude, TagCategory category) {
        super(uuid);
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
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
     * @return the category
     */
    public TagCategory getCategory() {
        return category;
    }

    /**
     * @param tagCategory the category to set
     */
    public void setCategory(TagCategory category) {
        this.category = category;
    }
}

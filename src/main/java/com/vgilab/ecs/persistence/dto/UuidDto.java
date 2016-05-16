package com.vgilab.ecs.persistence.dto;

/**
 *
 * @author smuellner
 */
public class UuidDto {
    
    private String uuid;

    public UuidDto(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
}

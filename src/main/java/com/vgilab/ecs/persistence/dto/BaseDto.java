package com.vgilab.ecs.persistence.dto;

/**
 *
 * @author smuellner
 */
public class BaseDto {
    
    private Long id;

    public BaseDto(Long id) {
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}

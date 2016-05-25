package com.vgilab.ecs.rest.response;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class CreateMediaResponse {
    
    @NotNull
    private String mediaId;

    /**
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
}

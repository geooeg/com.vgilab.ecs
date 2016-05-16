package com.vgilab.ecs.rest.response;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class CreateMoodResponse {
    
    @NotNull
    private String moodId;

    /**
     * @return the moodId
     */
    public String getMoodId() {
        return moodId;
    }

    /**
     * @param moodId the moodId to set
     */
    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }
}

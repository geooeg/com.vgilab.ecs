package com.vgilab.ecs.rest.resource;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class CreateTripResource {
    
    @NotNull
    private String deviceId;

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
}

package com.vgilab.ecs.rest.resource;

import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
public class DeviceWithAuthorizationResource extends DeviceResource {
    
    @NotNull
    private String authorizationCode;

    /**
     * @return the authorizationCode
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * @param authorizationCode the authorizationCode to set
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}

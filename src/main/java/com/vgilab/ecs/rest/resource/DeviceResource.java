package com.vgilab.ecs.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smuellner
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class DeviceResource {
        
    private String identifierForVendor;
    
    @NotNull
    private String maker;

    @NotNull
    private String model;

    private String software;

    private String source;
       
    /**
     * @return the IdentifierForVendor
     */
    public String getIdentifierForVendor() {
        return identifierForVendor;
    }

    /**
     * @param identifierForVendor the identifierForVendor to set
     */
    public void setIdentifierForVendor(String identifierForVendor) {
        this.identifierForVendor = identifierForVendor;
    }
    
    /**
     * @return the maker
     */
    public String getMaker() {
        return maker;
    }

    /**
     * @param maker the maker to set
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the software
     */
    public String getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(String software) {
        this.software = software;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }
}

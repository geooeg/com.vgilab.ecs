package com.vgilab.ecs.rest;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ljzhang
 */
public class PositionBatchResource {

    @NotNull
    private String maker;

    @NotNull
    private String model;

    private String software;

    private String source;

    private List<PositionResource> positions;

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

    /**
     * @return the positions
     */
    public List<PositionResource> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<PositionResource> positions) {
        this.positions = positions;
    }
    
}

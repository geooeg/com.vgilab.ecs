package com.vgilab.ecs.persistence.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "devices")
public class DeviceEntity extends UuidEntity {
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "name")
    private String name;
    
    @Column(name = "maker")
    private String maker;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "software")
    private String software;
    
    @Column(name = "source")
    private String source;
    
    @Column(name = "identifier_for_vendor")
    private String identifierForVendor;
            
    @OneToMany(mappedBy="device", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TripEntity> trips = new LinkedList<>();
   
    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the trips
     */
    public List<TripEntity> getTrips() {
        return trips;
    }

    /**
     * @param trips the trips to set
     */
    public void setTrips(List<TripEntity> trips) {
        this.trips = trips;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DeviceEntity)) {
            return false;
        }
        DeviceEntity other = (DeviceEntity) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.Device[ id=" + getId() + " ]";
    }

    /**
     * @return the identifierForVendor
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


}

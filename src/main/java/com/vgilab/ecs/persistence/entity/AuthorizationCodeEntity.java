package com.vgilab.ecs.persistence.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smuellner
 */
@Entity
@Table(name = "authorization_codes")
public class AuthorizationCodeEntity extends UuidEntity {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_until_time", nullable = false)
    private Calendar validUntilTime;

    /**
     * @return the validUntilTime
     */
    public Calendar getValidUntilTime() {
        return validUntilTime;
    }

    /**
     * @param validUntilTime the validUntilTime to set
     */
    public void setValidUntilTime(Calendar validUntilTime) {
        this.validUntilTime = validUntilTime;
    }
        
    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.AuthorizationCodeEntity[ id=" + this.getId() + " ]";
    }

    @PrePersist
    @Override
    public void prePersist() {
        this.setValidUntilTime(Calendar.getInstance());
        this.getValidUntilTime().add(Calendar.MINUTE, 2);
    }
}

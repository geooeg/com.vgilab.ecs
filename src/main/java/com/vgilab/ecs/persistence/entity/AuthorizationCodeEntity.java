package com.vgilab.ecs.persistence.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class AuthorizationCodeEntity extends BaseEntity<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_until_time", nullable = false)
    private Calendar validUntilTime;

    @Override
    public String getId() {
        return id;
    }

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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AuthorizationCodeEntity)) {
            return false;
        }
        AuthorizationCodeEntity other = (AuthorizationCodeEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.vgilab.ecs.persistence.entity.AuthorizationCodeEntity[ id=" + id + " ]";
    }

    @PrePersist
    @Override
    public void prePersist() {
        this.setValidUntilTime(Calendar.getInstance());
        this.getValidUntilTime().add(Calendar.MINUTE, 2);
    }
}

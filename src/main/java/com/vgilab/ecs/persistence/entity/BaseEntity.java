package com.vgilab.ecs.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author smuellner
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_time", nullable = true)
    private Calendar modificationTime;

    @Version
    private long version;

    public abstract ID getId();

    public Calendar getCreationTime() {
        return creationTime;
    }

    public Calendar getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        this.creationTime = Calendar.getInstance();
        this.modificationTime = Calendar.getInstance();
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = Calendar.getInstance();
    }
}
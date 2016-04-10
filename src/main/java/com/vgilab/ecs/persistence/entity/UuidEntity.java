package com.vgilab.ecs.persistence.entity;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author smuellner
 */
@MappedSuperclass
public abstract class UuidEntity extends BaseEntity<String> {
    
    @Id
    private String id;

    public UuidEntity() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseEntity)) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        return getId().equals(other.getId());
    }
}

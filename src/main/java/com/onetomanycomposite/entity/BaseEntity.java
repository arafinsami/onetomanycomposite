package com.onetomanycomposite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date created;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;

    @PrePersist
    public void prePersist() {
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = new Date();
    }
}

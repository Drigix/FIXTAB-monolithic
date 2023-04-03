package com.fixtab.app.models.db;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {

    @Column(name="deleted")
    private Boolean deleted;
}

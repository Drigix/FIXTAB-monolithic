package com.fixtab.app.models.db;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name="createddate")
    private Date createdDate;

    @CreatedBy
    @Column(name="createdby")
    private String createdBy;

    @LastModifiedDate
    @Column(name="modifieddate")
    private Date modifiedDate;

    @LastModifiedBy
    @Column(name="modifiedby")
    private String modifiedBy;

    @Column(name="deleted")
    @Value("${some.key:true}")
    private Boolean deleted;
}

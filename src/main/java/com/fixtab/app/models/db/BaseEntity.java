package com.fixtab.app.models.db;

import com.fixtab.app.models.db.employees.EmployeeModel;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

    @CreatedBy
    @Column(name="modifiedby")
    private String modifiedBy;

    @Column(name="deleted")
    @Value("${some.key:true}")
    private Boolean deleted;
}

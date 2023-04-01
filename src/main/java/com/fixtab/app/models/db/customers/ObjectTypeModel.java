package com.fixtab.app.models.db.customers;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "objecttype")
@Entity
public class ObjectTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codetypeid")
    private int codeTypeId;

    @Column(name = "name_type")
    private String nameType;
}

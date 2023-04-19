package com.fixtab.app.models.db.customers;

import com.fixtab.app.models.db.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "targetobject")
@Entity
public class TargetObjectModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "targetid")
    private int targetId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "clientid")
    private ClientModel client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objecttypeid", nullable = true)
    private ObjectTypeModel objectType;
}

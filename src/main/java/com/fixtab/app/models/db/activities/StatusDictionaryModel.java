package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "statusdictionary")
@Entity
public class StatusDictionaryModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusid")
    private int statusId;

    @Column(name = "status")
    private String name;
}

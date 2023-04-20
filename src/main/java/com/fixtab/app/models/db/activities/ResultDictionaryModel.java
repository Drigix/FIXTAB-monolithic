package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resultdictionary")
@Entity
public class ResultDictionaryModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultid")
    private int resultId;

    @Column(name = "result")
    private String name;
}

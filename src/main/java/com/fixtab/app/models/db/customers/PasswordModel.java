package com.fixtab.app.models.db.customers;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passwords")
@Entity
public class PasswordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpassword")
    private int idPassword;

    @Column(name = "passwordhash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "createdate")
    private Date createDate;

    @Column(name = "isactive")
    private boolean isActive;

    @Column(name = "employeeid")
    private Integer employeeId;
}
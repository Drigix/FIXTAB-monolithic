package com.fixtab.app.models.db.employees;

import com.fixtab.app.models.db.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "employees")
@Entity
public class EmployeeModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private int employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "targetobjectid")
    private Integer targetObjectId;

    @Column(name = "roleid")
    private Integer roleId;
}
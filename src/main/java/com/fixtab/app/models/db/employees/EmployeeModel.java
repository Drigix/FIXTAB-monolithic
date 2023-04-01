package com.fixtab.app.models.db.employees;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
@Entity
public class EmployeeModel {
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

    /*
    * Foreign keys
    */
    @Column(name = "addressid")
    private Integer addressId;
    @Column(name = "targetobjectid")
    private Integer targetObjectId;
    @Column(name = "roleid")
    private Integer roleId;
}
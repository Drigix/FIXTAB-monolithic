package com.fixtab.app.models.db.employees;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employeeroles")
@Entity
public class EmployeeRoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleId;

    @Column(name = "rolename")
    private String roleName;

    @Override
    public String getAuthority() {
        return "ROLE_" + getRoleName();
    }
}

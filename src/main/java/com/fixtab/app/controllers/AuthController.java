package com.fixtab.app.controllers;

import com.fixtab.app.infrastructure.PasswordHelperMethods;
import com.fixtab.app.models.db.customers.PasswordModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.models.requests.LoginRequest;
import com.fixtab.app.models.responses.LoginResponse;
import com.fixtab.app.respositories.PasswordRepository;
import com.fixtab.app.security.JwtTokenUtil;
import com.fixtab.app.services.implementations.EmployeeRoleServiceImpl;
import com.fixtab.app.services.interfaces.EmployeeRoleService;
import com.fixtab.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/public")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmployeeService employeeService;
    private final PasswordRepository passwordRepository;
    private final EmployeeRoleService employeeRoleService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        try {
            EmployeeModel employee = employeeService.loadUserByEmail(request.getEmail());
            Optional<PasswordModel> passwordOptional = passwordRepository.findByEmployeeId(employee.getEmployeeId());

            if (passwordOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Integer roleId = employee.getRoleId();
            Optional<EmployeeRoleModel> employeeRoleModelOptional = employeeRoleService.getEmployeeRole(roleId);

            if(employeeRoleModelOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            PasswordModel password = passwordOptional.get();
            String hashedPassword = PasswordHelperMethods.passwordToHash(request.getPassword(), employee.getEmail(), password.getSalt());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), hashedPassword);

            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            UserDetails user = (UserDetails) authenticate.getPrincipal();

            Map<String, Object> claims = new HashMap<>();
            claims.put("role",employeeRoleModelOptional.get().getAuthority());

            LoginResponse response = LoginResponse.builder()
                    .accessToken(jwtTokenUtil.generateToken(claims, user))
                    .expirationDate(new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY * 1000))
                    .build();
            return ResponseEntity.ok(response);

        } catch (DisabledException dex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

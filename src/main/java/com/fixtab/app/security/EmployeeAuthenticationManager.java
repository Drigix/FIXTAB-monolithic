package com.fixtab.app.security;

import com.fixtab.app.models.db.UserModel;
import com.fixtab.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeAuthenticationManager implements AuthenticationManager {

    private final EmployeeService employeeService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            Optional<UserModel> userOptional = Optional.ofNullable(employeeService.getUserModel(email));
            userOptional.orElseThrow(() -> new UsernameNotFoundException(
                    String.format("User: %s, not found", email)
            ));

            return userOptional.get();
        });
    }

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserModel userDetail = employeeService.getUserModel(authentication.getName());
        if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetail.getHashedPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());
    }
}

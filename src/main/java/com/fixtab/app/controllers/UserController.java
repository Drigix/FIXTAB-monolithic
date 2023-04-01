package com.fixtab.app.controllers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.UserRequest;
import com.fixtab.app.models.responses.UserResponse;
import com.fixtab.app.services.interfaces.EmployeeService;
import com.fixtab.app.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}


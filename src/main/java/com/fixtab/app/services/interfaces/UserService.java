package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.requests.UserRequest;
import com.fixtab.app.models.responses.UserResponse;

public interface UserService {

    public UserResponse createUser(UserRequest userRequest);
}

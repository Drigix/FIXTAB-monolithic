package com.fixtab.app.services.implementations;

import com.fixtab.app.infrastructure.mappers.UserMapper;
import com.fixtab.app.models.User;
import com.fixtab.app.models.requests.UserRequest;
import com.fixtab.app.models.responses.UserResponse;
import com.fixtab.app.respositories.UserRepository;
import com.fixtab.app.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);
        UserResponse userResponse = userMapper.toResponse(user);
        log.info("User {} created", userResponse.getFirstName());
        return userResponse;
    }

}

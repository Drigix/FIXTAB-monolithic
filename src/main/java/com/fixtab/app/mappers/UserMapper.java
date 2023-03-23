package com.fixtab.app.mappers;

import com.fixtab.app.models.User;
import com.fixtab.app.models.requests.UserRequest;
import com.fixtab.app.models.responses.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

    default User fromId (Long id) {
        if (id == null) {
            return null;
        }
        User user = User.builder().build();
        user.setId(id);
        return user;
    }
}

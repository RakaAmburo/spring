package com.database.infrastructure.controllers.user;

import com.database.domain.user.User;
import com.database.infrastructure.controllers.user.UserRequest;
import com.database.infrastructure.controllers.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    User toDomain(UserRequest request);

    UserResponse toResponse(User user);
}

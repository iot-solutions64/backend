package com.hydrosmart.security.domain.services.queryservices;

import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.domain.model.queries.GetAllUsersQuery;
import com.hydrosmart.security.domain.model.queries.GetUserByIdQuery;
import com.hydrosmart.security.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}

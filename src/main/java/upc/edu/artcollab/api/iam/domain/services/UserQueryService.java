package upc.edu.artcollab.api.iam.domain.services;

import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.domain.model.queries.GetAllUsersQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetUserByIdQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}

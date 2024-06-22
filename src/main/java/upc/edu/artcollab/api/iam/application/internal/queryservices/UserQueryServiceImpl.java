package upc.edu.artcollab.api.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.domain.model.queries.GetAllUsersQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetUserByIdQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetUserByUsernameQuery;
import upc.edu.artcollab.api.iam.domain.services.UserQueryService;
import upc.edu.artcollab.api.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
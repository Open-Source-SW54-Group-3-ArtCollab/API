package upc.edu.artcollab.api.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.iam.application.internal.outboundservices.hashing.HashingService;
import upc.edu.artcollab.api.iam.application.internal.outboundservices.tokens.TokenService;
import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.domain.model.commands.SignInCommand;
import upc.edu.artcollab.api.iam.domain.model.commands.SignUpCommand;
import upc.edu.artcollab.api.iam.domain.model.entities.Role;
import upc.edu.artcollab.api.iam.domain.model.valueobjects.Roles;
import upc.edu.artcollab.api.iam.domain.services.UserCommandService;
import upc.edu.artcollab.api.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import upc.edu.artcollab.api.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var stringRoles = command.roles();
        var roles = new ArrayList<Role>();
        if (stringRoles == null || stringRoles.isEmpty()) {
            var storedRole = roleRepository.findByName(Roles.ROLE_USER);
            storedRole.ifPresent(roles::add);
        } else {
            stringRoles.forEach(role -> {
                try {
                    Roles roleEnum = Roles.valueOf(role.toString().toUpperCase());
                    var storedRole = roleRepository.findByName(roleEnum);
                    storedRole.ifPresent(roles::add);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid role");
                }
            });
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
package upc.edu.artcollab.api.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.domain.model.commands.SignInCommand;
import upc.edu.artcollab.api.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
}

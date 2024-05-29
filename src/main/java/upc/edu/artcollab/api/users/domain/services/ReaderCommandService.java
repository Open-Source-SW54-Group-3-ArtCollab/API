package upc.edu.artcollab.api.users.domain.services;

import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;

import java.util.Optional;

public interface ReaderCommandService {
    Optional<Reader> handle(CreateReaderCommand command);
}

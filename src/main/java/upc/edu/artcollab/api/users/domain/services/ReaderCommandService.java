package upc.edu.artcollab.api.users.domain.services;

import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;

import java.util.Optional;

public interface ReaderCommandService {
    /**
     * Handle the creation of a reader
     * @param command the command to create a reader
     * @return the created reader
     */
    Optional<Reader> handle(CreateReaderCommand command);
}

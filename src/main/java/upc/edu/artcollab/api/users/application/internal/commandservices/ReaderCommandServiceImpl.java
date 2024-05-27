package upc.edu.artcollab.api.users.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;
import upc.edu.artcollab.api.users.domain.model.services.ReaderCommandService;
import upc.edu.artcollab.api.users.infrastructure.persistence.jpa.ReaderRepository;

import java.util.Optional;

@Service
public class ReaderCommandServiceImpl implements ReaderCommandService {
    private final ReaderRepository readerRepository;

    public ReaderCommandServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Optional<Reader> handle(CreateReaderCommand command) {
        if (readerRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("The email is already in use.");
        }
        if (readerRepository.existsByUsername(command.username())) {
            throw new IllegalArgumentException("The username is already in use.");
        }

        var reader = new Reader(command);
        var createdReader = readerRepository.save(reader);

        return Optional.of(createdReader);
    }
}

package upc.edu.artcollab.api.users.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;
import upc.edu.artcollab.api.users.domain.model.commands.DeleteReaderCommand;
import upc.edu.artcollab.api.users.domain.model.commands.UpdateReaderCommand;
import upc.edu.artcollab.api.users.domain.model.exceptions.EmailAleradyExistsException;
import upc.edu.artcollab.api.users.domain.model.exceptions.InvalidUserTypeException;
import upc.edu.artcollab.api.users.domain.model.exceptions.UsernameAlreadyExistsException;
import upc.edu.artcollab.api.users.domain.services.ReaderCommandService;
import upc.edu.artcollab.api.users.infrastructure.persistence.jpa.ReaderRepository;

import java.util.Optional;

@Service
public class ReaderCommandServiceImpl implements ReaderCommandService {
    private final ReaderRepository readerRepository;

    public ReaderCommandServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    /**
     * Handle the creation of a reader
     * @param command the command to create a reader
     * @return the created reader
     */
    @Override
    public Optional<Reader> handle(CreateReaderCommand command) {
        if (readerRepository.existsByEmail(command.email())) {
            throw new EmailAleradyExistsException("The email is already in use.");
        }
        if (readerRepository.existsByUsername(command.username())) {
            throw new UsernameAlreadyExistsException("The username is already in use.");
        }
        var reader = new Reader(command);
        var createdReader = readerRepository.save(reader);

        return Optional.of(createdReader);
    }

    @Override
    public Optional<Reader> handle(DeleteReaderCommand command) {
        var reader = readerRepository.findById(command.id());
        if (reader.isEmpty()) {
            return Optional.empty();
        }
        readerRepository.delete(reader.get());
        return reader;
    }

    @Override
    public Optional<Reader> handle(UpdateReaderCommand command) {
        var reader = readerRepository.findById(command.id());
        if (reader.isEmpty()) {
            return Optional.empty();
        }
        reader.get().setEmail(command.email());
        reader.get().setUsername(command.username());
        reader.get().setPassword(command.password());
       reader.get().setImgUrl(command.imgUrl());
       reader.get().setType(command.type());
       reader.get().setName(command.name());
       return reader;

    }
}

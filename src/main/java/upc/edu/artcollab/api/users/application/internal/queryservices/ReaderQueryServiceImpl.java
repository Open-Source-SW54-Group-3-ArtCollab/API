package upc.edu.artcollab.api.users.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;
import upc.edu.artcollab.api.users.domain.services.ReaderQueryService;
import upc.edu.artcollab.api.users.infrastructure.persistence.jpa.ReaderRepository;

import java.util.Optional;

@Service
public class ReaderQueryServiceImpl implements ReaderQueryService{
    private final ReaderRepository readerRepository;

    public ReaderQueryServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Optional<Reader> handle(GetReaderByEmailAndPasswordQuery query) {
        return readerRepository.findByEmailAndPassword(query.email(), query.password());
    }

    @Override
    public Optional<Reader> handle(GetReaderByIdQuery query) {
        return readerRepository.findById(query.id());
    }
}

package upc.edu.artcollab.api.users.domain.model.services;

import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;

import java.util.Optional;

public interface ReaderQueryService {
    Optional<Reader> handle(GetReaderByEmailAndPasswordQuery query);
    Optional<Reader> handle(GetReaderByIdQuery query);
}

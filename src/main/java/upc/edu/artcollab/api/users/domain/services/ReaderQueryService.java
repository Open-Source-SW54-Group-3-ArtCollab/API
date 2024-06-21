package upc.edu.artcollab.api.users.domain.services;

import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;

import java.util.Optional;

public interface ReaderQueryService {
    /**
     * Handle the query to get a reader by email and password
     * @param query the query to get a reader by email and password
     * @return the reader
     */
    Optional<Reader> handle(GetReaderByEmailAndPasswordQuery query);
    Optional<Reader> handle(GetReaderByIdQuery query);
}

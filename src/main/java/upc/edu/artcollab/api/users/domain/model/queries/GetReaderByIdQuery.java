package upc.edu.artcollab.api.users.domain.model.queries;

public record GetReaderByIdQuery(Long id) {
    public GetReaderByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("The id must not be null or empty.");
        }
    }
}

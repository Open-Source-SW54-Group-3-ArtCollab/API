package upc.edu.artcollab.api.monetization.domain.model.queries;

public record GetCommisionByIdQuery(Long id) {

    public GetCommisionByIdQuery {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id cannot be null or negative");
        }
    }
}

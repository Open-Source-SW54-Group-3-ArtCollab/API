package upc.edu.artcollab.api.monetization.domain.model.queries;

public record GetSubscriptionByIdQuery(Long id) {

    public GetSubscriptionByIdQuery {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id cannot be null or negative");
        }
    }
}

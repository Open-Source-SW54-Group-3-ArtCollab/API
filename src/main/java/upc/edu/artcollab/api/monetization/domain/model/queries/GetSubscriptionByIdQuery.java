package upc.edu.artcollab.api.monetization.domain.model.queries;

/**
 * GetSubscriptionByIdQuery
 * <p>
 * This record class encapsulates the query to get a subscription by its id. It contains the id of the subscription to be retrieved.
 * An IllegalArgumentException is thrown if the id is null or negative.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record GetSubscriptionByIdQuery(Long id) {

    /**
     * Constructor for GetSubscriptionByIdQuery.
     * <p>
     * Validates the id to ensure it is not null or negative.
     * </p>
     * @throws IllegalArgumentException if id is null or negative.
     */
    public GetSubscriptionByIdQuery {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id cannot be null or negative");
        }
    }
}
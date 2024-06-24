package upc.edu.artcollab.api.monetization.domain.model.queries;

/**
 * GetCommisionByIdQuery
 * <p>
 * This record class encapsulates the query to get a commission by its id. It contains the id of the commission to be retrieved.
 * An IllegalArgumentException is thrown if the id is null or negative.
 * </p>
 * @author U202213375 Italo Luna Capuñay
 * @version 1.0
 */
public record GetCommisionByIdQuery(Long id) {

    /**
     * Constructor for GetCommisionByIdQuery.
     * <p>
     * Validates the id to ensure it is not null or negative.
     * </p>
     * @throws IllegalArgumentException if id is null or negative.
     */
    public GetCommisionByIdQuery {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id cannot be null or negative");
        }
    }
}
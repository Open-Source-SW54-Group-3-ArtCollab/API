package upc.edu.artcollab.api.monetization.domain.model.queries;

/**
 * GetCommisionsByAmountGreatherThanQuery
 * <p>
 * This record class encapsulates the query to get commissions by an amount greater than a specified value.
 * It contains the amount that the commissions should be greater than.
 * An IllegalArgumentException is thrown if the amount is negative.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record GetCommisionsByAmountGreatherThanQuery(double amount) {

    /**
     * Constructor for GetCommisionsByAmountGreatherThanQuery.
     * <p>
     * Validates the amount to ensure it is not negative.
     * </p>
     * @throws IllegalArgumentException if amount is negative.
     */
    public GetCommisionsByAmountGreatherThanQuery {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
}
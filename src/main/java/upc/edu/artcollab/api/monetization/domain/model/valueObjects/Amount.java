/**
 * @summary
 * This class represents a value object that holds the amount of money.
 * Declares as record for immutability
 */

package upc.edu.artcollab.api.monetization.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Amount(Double amount) {
    /**
     * @summary
     * This method creates an instance of Amount.
     * @param amount
     * The amount of money.
     * @return
     * An instance of Amount.
     */
    @JsonCreator
    public static Amount of(Double amount) {
        return new Amount(amount);
    }

    /**
     * @summary
     * This method checks if the amount is negative.
     * @param amount
     * The amount of money.
     */

    public Amount {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
}

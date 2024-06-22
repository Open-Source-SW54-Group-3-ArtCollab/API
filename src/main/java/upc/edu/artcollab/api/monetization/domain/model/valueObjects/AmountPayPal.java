package upc.edu.artcollab.api.monetization.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.paypal.api.payments.Amount;

public record AmountPayPal (Amount amount) {

    /**
     * Creates a new AmountPayPal instance.
     *
     * @param amount the amount
     * @return the amountPayPal
     */
    @JsonCreator
    public static AmountPayPal of(Amount amount) {
        return new AmountPayPal(amount);
    }

    /**
     * Checks if the amount is null.
     *
     * @param amount the amount
     */

    public AmountPayPal {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
    }
}

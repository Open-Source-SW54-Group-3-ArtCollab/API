package upc.edu.artcollab.api.monetization.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.paypal.api.payments.Amount;

public record AmountPayPal (Amount amount) {

    @JsonCreator
    public static AmountPayPal of(Amount amount) {
        return new AmountPayPal(amount);
    }

    public AmountPayPal {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
    }
}

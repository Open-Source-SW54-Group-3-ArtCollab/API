package upc.edu.artcollab.api.monetization.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.paypal.api.payments.Currency;

public record CurrencyPayPal(Currency currency) {
    @JsonCreator
    public static CurrencyPayPal of(Currency currency) {
        return new CurrencyPayPal(currency);
    }

    public CurrencyPayPal {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
    }
}

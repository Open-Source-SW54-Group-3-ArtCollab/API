package upc.edu.artcollab.api.monetization.interfaces.rest.resources;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;

public record PaymentResource(Amount amount, Currency currency, String status, String description){
}

package upc.edu.artcollab.api.monetization.domain.model.commands;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;

public record CreatePaymentCommand(Amount amount, Currency currency, String status, String description) {
}

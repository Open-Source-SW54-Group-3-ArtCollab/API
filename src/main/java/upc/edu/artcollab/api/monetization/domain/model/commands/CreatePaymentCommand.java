package upc.edu.artcollab.api.monetization.domain.model.commands;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;

/**
 * CreatePaymentCommand
 * <p>
 * This record class encapsulates the command to create a payment. It contains the amount, currency, status, and description of the payment.
 * </p>
 * @author U202213375 Italo Luna Capuñay
 * @version 1.0
 */
public record CreatePaymentCommand(
        /**
         * The amount of the payment.
         */
        Amount amount,

        /**
         * The currency of the payment.
         */
        Currency currency,

        /**
         * The status of the payment.
         */
        String status,

        /**
         * The description of the payment.
         */
        String description) {
}
package upc.edu.artcollab.api.monetization.application.internal.commandservices;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;
import upc.edu.artcollab.api.monetization.domain.services.PaymentCommandService;

import java.util.Collections;

/**
 * PaymentCommandServiceImpl
 * <p>
 * This class implements the PaymentCommandService interface and provides the implementation for the
 * methods declared in the interface.
 * </p>
 * @author U202213375 Italo Luna Capuñay
 * @version 1.0
 */
@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final APIContext apiContext;

    /**
     * Constructor for PaymentCommandServiceImpl.
     *
     * @param apiContext the APIContext for PayPal payments
     */
    @Autowired
    public PaymentCommandServiceImpl(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    /**
     * Creates a payment using the provided command.
     *
     * @param command the command containing the details of the payment
     */
    @Override
    public void createPayment(CreatePaymentCommand command) {
        Amount amount = new Amount();
        amount.setCurrency(command.currency().getCurrency());
        amount.setTotal(command.amount().getTotal());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(command.description());

        Payment payment = new Payment();
        payment.setIntent("subscription");

        payment.setTransactions(Collections.singletonList(transaction));

        try {
            Payment createdPayment = payment.create(apiContext);

            System.out.println("ID del pago  " + createdPayment.getId());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a payment with the provided paymentId and payerId.
     *
     * @param paymentId the ID of the payment
     * @param payerId the ID of the payer
     * @return the executed Payment
     * @throws PayPalRESTException if there is an error during the execution of the payment
     */
    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}
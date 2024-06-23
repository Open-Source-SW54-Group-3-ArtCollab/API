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
 * Implementation of the PaymentCommandService interface
 * <p>
 *     This class is responsible for implementing the methods defined in the PaymentCommandService interface.
 * </p>
 * @author U20221271 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final APIContext apiContext;

    @Autowired
    public PaymentCommandServiceImpl(APIContext apiContext) {
        this.apiContext = apiContext;
    }

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

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}



package upc.edu.artcollab.api.monetization.domain.services;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;

/**
 * PaymentCommandService
 * <p>
 *     Service that handles the commands related to payments.
 * </p>
 * @author  Samira Alvarez Araguache
 * @version 1.0
 */
@Service
public interface PaymentCommandService {
    void createPayment(CreatePaymentCommand command);
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;


}

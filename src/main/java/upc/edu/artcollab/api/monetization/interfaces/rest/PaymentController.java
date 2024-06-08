package upc.edu.artcollab.api.monetization.interfaces.rest;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.application.internal.commandservices.PaymentCommandServiceImpl;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    /**
     * @summary
     * PaymentCommandServiceImpl object.
     * @param paymentCommandService The PaymentCommandServiceImpl object.
     */
    private final PaymentCommandServiceImpl paymentCommandService;

    /**
     * @summary
     * Constructor for PaymentController class.
     * @param paymentCommandService The PaymentCommandServiceImpl object.
     */
    @Autowired
    public PaymentController(PaymentCommandServiceImpl paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    /**
     * @summary
     * This method is used to create a payment.
     * @param command The CreatePaymentCommand object containing payment details.
     * @return A ResponseEntity indicating the result of the payment creation.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody CreatePaymentCommand command) {
        try {
            paymentCommandService.createPayment(command);
            return ResponseEntity.ok("Payment created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment");
        }
    }

    /**
     * @summary
     * This method is used to cancel a payment.
     * @return A ResponseEntity indicating that the payment was cancelled successfully.
     */
    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPay() {
        return ResponseEntity.ok("Payment cancelled");
    }

    /**
     * @summary
     * This method is used to execute a payment.
     * @param paymentId The ID of the payment.
     * @param payerId The ID of the payer.
     * @return A ResponseEntity containing the result of the payment execution.
     */
    @GetMapping("/success")
    public ResponseEntity<String> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentCommandService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return ResponseEntity.ok("Payment successful");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment not approved");
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing payment: " + e.getMessage());
        }
    }
//    @PostMapping("/create")
//    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
//        Optional<Payment> paymentOptional = paymentCommandService.createPayment(CreatePaymentCommand.fromResource(resource));
//        return paymentOptional.map(payment -> new ResponseEntity<>(PaymentResource.fromEntity(payment), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
//    }
}

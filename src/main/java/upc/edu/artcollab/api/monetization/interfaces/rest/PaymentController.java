package upc.edu.artcollab.api.monetization.interfaces.rest;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.application.internal.commandservices.PaymentCommandServiceImpl;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;

/**
 *  Payment Controller
 *  <p>
 *      This class is used to handle the REST API endpoints related to payments.
 *  </p>
 * @author Samira Alvarez Araguache
 * @version 1.0
 */
@RestController
@RequestMapping("/payments")
@Tag(name = "Payment", description = "Payment Controller, use Paypal external Service")
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden"),
})
public class PaymentController {

    /**
     * @summary PaymentCommandServiceImpl object.
     * @param paymentCommandService The PaymentCommandServiceImpl object.
     */
    private final PaymentCommandServiceImpl paymentCommandService;

    /**
     * @param paymentCommandService The PaymentCommandServiceImpl object.
     * @summary Constructor for PaymentController class.
     */

    @Autowired
    public PaymentController(PaymentCommandServiceImpl paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    /**
     * @param command The CreatePaymentCommand object containing payment details.
     * @return A ResponseEntity indicating the result of the payment creation.
     * @summary This method is used to create a payment.
     */
    @Operation(summary = "Create a payment")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Payment created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error creating payment")
    })
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
     * @return A ResponseEntity indicating that the payment was cancelled successfully.
     * @summary This method is used to cancel a payment.
     */
    @Operation(summary = "Cancel a payment")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Payment cancelled successfully")
    })
    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPay() {
        return ResponseEntity.ok("Payment cancelled");
    }

    /**
     * @param paymentId The ID of the payment.
     * @param payerId   The ID of the payer.
     * @return A ResponseEntity containing the result of the payment execution.
     * @summary This method is used to execute a payment.
     */
    @Operation(summary = "Execute a payment")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Payment successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error executing payment")
    })
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
}

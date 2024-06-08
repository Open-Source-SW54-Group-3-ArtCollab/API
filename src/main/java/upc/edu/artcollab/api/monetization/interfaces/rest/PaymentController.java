package upc.edu.artcollab.api.monetization.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upc.edu.artcollab.api.monetization.application.internal.commandservices.PaymentCommandServiceImpl;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentCommandServiceImpl paymentCommandService;

    @Autowired
    public PaymentController(PaymentCommandServiceImpl paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

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
    
//    @PostMapping("/create")
//    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
//        Optional<Payment> paymentOptional = paymentCommandService.createPayment(CreatePaymentCommand.fromResource(resource));
//        return paymentOptional.map(payment -> new ResponseEntity<>(PaymentResource.fromEntity(payment), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
//    }
}

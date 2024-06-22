package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Payment;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {

    public static PaymentResource toCommandFromResource(Payment payment) {
        return new PaymentResource(
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getDescription()
        );
    }
}

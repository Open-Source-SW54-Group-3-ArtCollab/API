package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand fromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.amount(),
                resource.currency(),
                resource.status(),
                resource.description()
        );
    }
}

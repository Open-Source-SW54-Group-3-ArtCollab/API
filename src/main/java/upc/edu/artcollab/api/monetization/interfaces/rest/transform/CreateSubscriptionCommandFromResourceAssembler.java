/**
 * @summary
 * This file contains the CreateSubscriptionCommandFromResourceAssembler class, which is used to transform the CreateSubscriptionResource object into a CreateSubscriptionCommand object.
 * This class contains a static method toCommandFromResource that takes a CreateSubscriptionResource object as input and returns a CreateSubscriptionCommand object.
 * The CreateSubscriptionResource object is used to represent the data received in the request body of the create subscription endpoint.
 * The CreateSubscriptionCommand object is used to represent the data needed to create a new subscription in the monetization module.
 * The CreateSubscriptionCommand object is used to pass the data to the CreateSubscriptionCommandHandler class, which is responsible for creating the new subscription.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {

    /**
     * @summary
     * This method takes a CreateSubscriptionResource object as input and returns a CreateSubscriptionCommand object.
     * The method creates a new CreateSubscriptionCommand object and sets the active status of the CreateSubscriptionResource object as the active status of the CreateSubscriptionCommand object.
     * @param resource
     * The CreateSubscriptionResource object to be transformed into a CreateSubscriptionCommand object.
     * @return
     * The CreateSubscriptionCommand object created from the CreateSubscriptionResource object.
     */

    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(resource.isActive());
    }
}

/**
 * @summary
 * This class is responsible for transforming a CreateCommisionResource object into a CreateCommisionCommand object.
 * The CreateCommisionResource object is used to represent the data received in the request body of the create commision endpoint.
 * The CreateCommisionCommand object is used to represent the data needed to create a new commision in the monetization module.
 * The CreateCommisionCommand object is used to pass the data to the CreateCommisionCommandHandler class, which is responsible for creating the new commision.
 * The CreateCommisionResource object is used to represent the data received in the request body of the create commision endpoint.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateCommisionResource;

public class CreateCommisionCommandFromResourceAssembler {

    /**
     * @summary
     * This method takes a CreateCommisionResource object as input and returns a CreateCommisionCommand object.
     * The method creates a new CreateCommisionCommand object and sets the amount and content of the CreateCommisionResource object as the amount and content of the CreateCommisionCommand object.
     * The method then returns the CreateCommisionCommand object.
     *
     * @param resource
     * The CreateCommisionResource object to be transformed into a CreateCommisionCommand object.
     * @return
     * The CreateCommisionCommand object created from the CreateCommisionResource object.
     */

    public static CreateCommisionCommand toCommandFromResource(CreateCommisionResource resource) {
        return new CreateCommisionCommand(resource.amount(), resource.content());
    }
}

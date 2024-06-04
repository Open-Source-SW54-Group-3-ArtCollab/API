/**
 * @summary
 * This file contains the CommisionResourceFromEntityAssembler class, which is used to transform the Commision entity into a CommisionResource object.
 * This class contains a static method fromEntity that takes a Commision object as input and returns a CommisionResource object.
 * The CommisionResource object is used to represent the Commision entity in the REST API.
 * The Commision entity is used to represent the commision details in the monetization module.
 * The CommisionResource object is used to represent the commision details in the REST API.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CommisionResource;

public class CommisionResourceFromEntityAssembler {

    /**
     * @summary
     * This method takes a Commision object as input and returns a CommisionResource object.
     * The method creates a new CommisionResource object and sets the id, amount, and content of the Commision object as the id, amount, and content of the CommisionResource object.
     * The method then returns the CommisionResource object.
     * @param commision
     * The Commision object to be transformed into a CommisionResource object.
     * @return
     * The CommisionResource object created from the Commision object.
     */

    public static CommisionResource toResourceFromEntity(Commision commision) {
        return new CommisionResource(commision.getId(),  commision.getAmount(), commision.getContent());
    }
}

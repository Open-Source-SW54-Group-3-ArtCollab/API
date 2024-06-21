package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteSubscriptionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.DeleteSubscriptionResource;

/**
 *  DeleteSubscriptionCommandFromResourceAssembler
 *  <p>
 *      This class is responsible for transforming a DeleteSubscriptionResource into a DeleteSubscriptionCommand.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class DeleteSubscriptionCommandFromResourceAssembler {

    public static DeleteSubscriptionCommand toCommandFromResource(DeleteSubscriptionResource resource){
        return new DeleteSubscriptionCommand(resource.id());
    }
}

package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.UpdateSubscriptionResource;

/**
 *  UpdateSubscriptionCommandFromResourceAssembler
 *   <p>
 *       This class is responsible for transforming the UpdateSubscriptionResource into a UpdateSubscriptionCommand
 *   </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class UpdateSubscriptionCommandFromResourceAssembler {

    public static UpdateSubscriptionCommand toCommandFromResource(long id,UpdateSubscriptionResource updateSubscriptionResource){
        return new UpdateSubscriptionCommand(id, updateSubscriptionResource.isActive());
    }
}

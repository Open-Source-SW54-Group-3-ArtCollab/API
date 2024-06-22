package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateCommisionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.UpdateCommisionResource;

/**
 *  UpdateCommisionCommandFromResourceAssembler
 *  <p>
 *      This class is responsible for converting a UpdateCommisionResource object into a UpdateCommisionCommand object.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class UpdateCommisionCommandFromResourceAssembler {
    public static UpdateCommisionCommand toCommandFromResource(long id,UpdateCommisionResource resource) {
        return new UpdateCommisionCommand(id,resource.amount(), resource.content());
    }
}

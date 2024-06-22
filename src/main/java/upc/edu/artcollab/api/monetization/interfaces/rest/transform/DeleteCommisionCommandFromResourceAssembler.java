package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteCommisionCommand;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.DeleteCommisionResource;

/**
 * DeleteCommisionCommandFromResourceAssembler
 * <p>
 *     Assembler class to transform a DeleteCommisionResource into a DeleteCommisionCommand
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class DeleteCommisionCommandFromResourceAssembler {
    public static DeleteCommisionCommand toCommandFromResource(DeleteCommisionResource resource) {
        return new DeleteCommisionCommand(resource.id());
    }
}

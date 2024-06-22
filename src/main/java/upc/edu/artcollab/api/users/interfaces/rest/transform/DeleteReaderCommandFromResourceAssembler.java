package upc.edu.artcollab.api.users.interfaces.rest.transform;

import upc.edu.artcollab.api.users.domain.model.commands.DeleteReaderCommand;
import upc.edu.artcollab.api.users.interfaces.rest.resources.DeleteReaderResource;

public class DeleteReaderCommandFromResourceAssembler {

    public static DeleteReaderCommand toCommandFromResource(DeleteReaderResource resource){
        return new DeleteReaderCommand(resource.id());
    }
}

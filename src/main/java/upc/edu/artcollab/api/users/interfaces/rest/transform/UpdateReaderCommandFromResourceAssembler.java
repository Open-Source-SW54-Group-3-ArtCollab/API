package upc.edu.artcollab.api.users.interfaces.rest.transform;

import upc.edu.artcollab.api.users.domain.model.commands.UpdateReaderCommand;
import upc.edu.artcollab.api.users.interfaces.rest.resources.UpdateReaderResource;

public class UpdateReaderCommandFromResourceAssembler {
    public static UpdateReaderCommand toCommandFromResource(long id,UpdateReaderResource updateReaderResource){
        return new UpdateReaderCommand(id,updateReaderResource.name(),updateReaderResource.username(),
                updateReaderResource.email(), updateReaderResource.password(),updateReaderResource.type(), updateReaderResource.imgUrl());
    }
}

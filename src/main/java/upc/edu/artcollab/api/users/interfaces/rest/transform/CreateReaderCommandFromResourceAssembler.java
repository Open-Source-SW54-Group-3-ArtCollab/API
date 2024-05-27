package upc.edu.artcollab.api.users.interfaces.rest.transform;

import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;
import upc.edu.artcollab.api.users.interfaces.rest.resources.CreateReaderResource;

public class CreateReaderCommandFromResourceAssembler {
    public static CreateReaderCommand toCommandFromResource(CreateReaderResource resource) {
        return new CreateReaderCommand(resource.name(), resource.username(), resource.email(), resource.password(), resource.type(), resource.imgUrl());
    }
}

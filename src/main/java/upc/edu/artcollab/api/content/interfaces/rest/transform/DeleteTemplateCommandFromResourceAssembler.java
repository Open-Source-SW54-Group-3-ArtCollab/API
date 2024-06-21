package upc.edu.artcollab.api.content.interfaces.rest.transform;

import upc.edu.artcollab.api.content.domain.model.commands.DeleteTemplateCommand;
import upc.edu.artcollab.api.content.interfaces.rest.resources.DeleteTemplateResource;

public class DeleteTemplateCommandFromResourceAssembler {
    public static DeleteTemplateCommand toCommandFromResource(DeleteTemplateResource resource) {
        return new DeleteTemplateCommand(resource.id());
    }
}

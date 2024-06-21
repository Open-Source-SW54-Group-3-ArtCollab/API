package upc.edu.artcollab.api.content.interfaces.rest.transform;

import upc.edu.artcollab.api.content.domain.model.commands.UpdateTemplateCommand;
import upc.edu.artcollab.api.content.interfaces.rest.resources.UpdateTemplateResource;

public class UpdateTemplateCommandFromResourceAssembler {
    public static UpdateTemplateCommand toCommandFromResource(Integer id,UpdateTemplateResource updateTemplateResource) {
        return new UpdateTemplateCommand(
                id, updateTemplateResource.title(), updateTemplateResource.description(), updateTemplateResource.type(), updateTemplateResource.imgURL(), updateTemplateResource.views(),
                updateTemplateResource.likes(), updateTemplateResource.genre(), updateTemplateResource.templateState_Id(),
                updateTemplateResource.templateHistory_Id(),updateTemplateResource.portfolio_Id()
        );
    }
}

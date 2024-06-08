package upc.edu.artcollab.api.content.interfaces.rest.transform;

import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.content.interfaces.rest.resources.CreateTemplateResource;

/**
 * Assembler for converting CreateTemplateResource objects to CreateTemplateCommand commands.
 *
 * <p>This assembler provides a method to convert a CreateTemplateResource object, which is typically used
 * in API requests, to a CreateTemplateCommand, which is used to execute business logic.</p>
 */
public class CreateTemplateCommandFromResourceAssembler {

    /**
     * Converts a CreateTemplateResource to a CreateTemplateCommand.
     *
     * @param resource the CreateTemplateResource to convert
     * @return the converted CreateTemplateCommand
     */
    public static CreateTemplateCommand toCommandFromResource(CreateTemplateResource resource) {
        return new CreateTemplateCommand(resource.title(), resource.description(), resource.type(), resource.imgURL(), resource.views(), resource.likes(), resource.genre(), resource.templateState_Id(), resource.templateHistory_Id(), resource.portfolio_Id());
    }
}

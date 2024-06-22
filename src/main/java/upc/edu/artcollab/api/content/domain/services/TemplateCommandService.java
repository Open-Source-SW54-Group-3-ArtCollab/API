package upc.edu.artcollab.api.content.domain.services;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.commands.DeleteTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.commands.UpdateTemplateCommand;

import java.util.Optional;

/**
 * The TemplateCommandService interface provides the methods to handle the commands related to the Template entity.
 * <p>
 *     This interface provides the methods to handle the commands related to the Template entity.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
public interface TemplateCommandService {
    Optional<Template> handle(CreateTemplateCommand command);
    Optional<Template> handle(DeleteTemplateCommand command);
    Optional<Template> handle(UpdateTemplateCommand command);
}

package upc.edu.artcollab.api.content.domain.services;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;

import java.util.Optional;

public interface TemplateCommandService {
    Optional<Template> handle(CreateTemplateCommand command);
}

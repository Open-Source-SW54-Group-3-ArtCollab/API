/**
 * TemplateCommandService Implementation.
 *
 * @summary
 * The TemplateCommandServiceImpl class is an implementation of the TemplateCommandService interface.
 * It is responsible for handling the CreateTemplateCommand command.
 */

package upc.edu.artcollab.api.content.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.content.domain.services.TemplateCommandService;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.TemplateRepository;

import java.util.Optional;

@Service
public class TemplateCommandServiceImpl implements TemplateCommandService {
    private final TemplateRepository templateRepository;

    public TemplateCommandServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Handles the CreateTemplateCommand command.
     *
     * @param command - the CreateTemplateCommand command
     * @return an Optional of Template
     */
    @Override
    public Optional<Template> handle(CreateTemplateCommand command) {
        if (templateRepository.existsByDescription(command.description())) {
            throw new IllegalArgumentException("Template with same description already exists");
        }
        if (templateRepository.existsByImgURL(command.imgURL())) {
            throw new IllegalArgumentException("Template with same image URL already exists");
        }
        var template = new Template(command);
        var createdTemplate = templateRepository.save(template);
        return Optional.of(createdTemplate);
    }
}

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
import upc.edu.artcollab.api.content.domain.model.entities.Portfolio;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateHistory;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateState;
import upc.edu.artcollab.api.content.domain.services.TemplateCommandService;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.PortfolioRepository;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.TemplateHistoryRepository;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.TemplateRepository;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.TemplateStateRepository;

import java.util.Optional;

@Service
public class TemplateCommandServiceImpl implements TemplateCommandService {
    private final TemplateRepository templateRepository;
    private final TemplateHistoryRepository templateHistoryRepository;
    private final TemplateStateRepository templateStateRepository;
    private final PortfolioRepository portfolioRepository;

    public TemplateCommandServiceImpl(TemplateRepository templateRepository, TemplateHistoryRepository templateHistoryRepository, TemplateStateRepository templateStateRepository, PortfolioRepository portfolioRepository) {
        this.templateRepository = templateRepository;
        this.portfolioRepository = portfolioRepository;
        this.templateHistoryRepository = templateHistoryRepository;
        this.templateStateRepository = templateStateRepository;
    }

    @Override
    public Optional<Template> handle(CreateTemplateCommand command) {
        if (templateRepository.existsByDescription(command.description())) {
            throw new IllegalArgumentException("Template with same description already exists");
        }
        if (templateRepository.existsByImgURL(command.imgURL())) {
            throw new IllegalArgumentException("Template with same image URL already exists");
        }

        TemplateState templateState = new TemplateState();
        templateState = templateStateRepository.save(templateState);

        TemplateHistory templateHistory = new TemplateHistory();
        templateHistory = templateHistoryRepository.save(templateHistory);

        Portfolio portfolio = new Portfolio();
        portfolio = portfolioRepository.save(portfolio);

        var template = new Template(command, templateState, templateHistory, portfolio);
        var createdTemplate = templateRepository.save(template);
        return Optional.of(createdTemplate);
    }
}

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
import upc.edu.artcollab.api.content.domain.model.commands.DeleteTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.commands.UpdateTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.entities.Portfolio;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateHistory;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateState;
import upc.edu.artcollab.api.content.domain.model.exceptions.TemplateWithTheSameDescriptionAlreadyExistsException;
import upc.edu.artcollab.api.content.domain.model.exceptions.TemplateWithTheSameImgUrlAlreadyExistsException;
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
            throw new TemplateWithTheSameDescriptionAlreadyExistsException("Template with same description already exists");
        }
        if (templateRepository.existsByImgURL(command.imgURL())) {
            throw new TemplateWithTheSameImgUrlAlreadyExistsException("Template with same imgURL already exists");
        }

        TemplateState templateState = new TemplateState();
        templateState = templateStateRepository.save(templateState);

        TemplateHistory templateHistory = new TemplateHistory();
        templateHistory = templateHistoryRepository.save(templateHistory);

        Portfolio portfolio = portfolioRepository.findById(command.portfolio_Id())
                .orElseGet(() -> {
                    Portfolio newPortfolio = new Portfolio(command.portfolio_Id(), "Default title", "Default description", 0);
                    return portfolioRepository.save(newPortfolio);
                });

        var template = new Template(command, templateState, templateHistory, portfolio);
        var createdTemplate = templateRepository.save(template);
        return Optional.of(createdTemplate);
    }

    @Override
    public Optional<Template> handle(DeleteTemplateCommand command) {
        var template = templateRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
        templateRepository.delete(template);
        return Optional.of(template);
    }

    @Override
    public Optional<Template> handle(UpdateTemplateCommand command) {
     var template = templateRepository.findById(command.id());
        if (template.isEmpty()) {
            throw new IllegalArgumentException("Template not found");
        }
        template.get().setTitle(command.title());
        template.get().setDescription(command.description());
         template.get().setType(command.type());
        template.get().setImgURL(command.imgURL());
        template.get().setViews(command.views());
        template.get().setLikes(command.likes());
        template.get().setGenre(command.genre());
        template.get().setTemplateState(templateStateRepository.findById(command.templateState_Id()).orElseThrow(() -> new IllegalArgumentException("TemplateState not found")));
        template.get().setTemplateHistory(templateHistoryRepository.findById(command.templateHistory_Id()).orElseThrow(() -> new IllegalArgumentException("TemplateHistory not found")));
        template.get().setPortfolio(portfolioRepository.findById(command.portfolio_Id()).orElseThrow(() -> new IllegalArgumentException("Portfolio not found")));
        return template;
    }

}

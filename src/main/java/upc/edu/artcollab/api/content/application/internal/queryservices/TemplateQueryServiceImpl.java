/**
 * TemplateQueryService Implementation
 *
 * @summary
 * The TemplateQueryServiceImpl class is an implementation of the TemplateQueryService interface.
 * It is responsible for handling the GetAllTemplatesByGenreQuery and GetTemplateByIdQuery queries.
 *
 */
package upc.edu.artcollab.api.content.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;
import upc.edu.artcollab.api.content.domain.services.TemplateQueryService;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories.TemplateRepository;

import java.util.List;
import java.util.Optional;

/**
 * TemplateQueryServiceImpl
 * <p>
 *     This class implements the TemplateQueryService interface. It contains the methods to handle the queries
 *     GetAllTemplatesByGenreQuery and GetTemplateByIdQuery.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@Service
public class TemplateQueryServiceImpl implements TemplateQueryService {

    private final TemplateRepository templateRepository;

    /**
     * TemplateQueryServiceImpl constructor
     *
     * @param templateRepository - the TemplateRepository
     */
    public TemplateQueryServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Handles the GetAllTemplatesByGenreQuery query.
     *
     * @param query - the GetAllTemplatesByGenreQuery query
     * @return a list of Template
     */
    @Override
    public List<Template> handle(GetAllTemplatesByGenreQuery query) {
        return templateRepository.findAllByGenre(query.genre());
    }

    /**
     * Handles the GetTemplateByIdQuery query.
     *
     * @param query - the GetTemplateByIdQuery query
     * @return an Optional of Template
     */
    @Override
    public Optional<Template> handle(GetTemplateByIdQuery query) {
        return templateRepository.findById(query.id());
    }

    /**
     * Handles the GetAllTemplatesQuery query.
     * @param query - the GetAllTemplatesQuery query
     * @return a list of Template
     */
    @Override
    public List<Template> handle(GetAllTemplatesQuery query) {
        return templateRepository.findAll();
    }
}

package upc.edu.artcollab.api.content.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;
import upc.edu.artcollab.api.content.domain.services.TemplateQueryService;
import upc.edu.artcollab.api.content.infrastructure.persistence.jpa.TemplateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateQueryServiceImpl implements TemplateQueryService {

    private final TemplateRepository templateRepository;

    public TemplateQueryServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public List<Template> handle(GetAllTemplatesByGenreQuery query) {
        return templateRepository.findAllByGenre(query.genre());
    }

    @Override
    public Optional<Template> handle(GetTemplateByIdQuery query) {
        return templateRepository.findById(query.id());
    }
}

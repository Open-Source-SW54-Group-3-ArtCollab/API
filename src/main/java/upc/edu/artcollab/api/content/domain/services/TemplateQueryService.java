package upc.edu.artcollab.api.content.domain.services;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;

import java.util.Optional;

public interface TemplateQueryService {
    Optional<Template> handle(GetTemplateByIdQuery query);
    Optional<Template> handle(GetTemplateByGenreQuery query);
}

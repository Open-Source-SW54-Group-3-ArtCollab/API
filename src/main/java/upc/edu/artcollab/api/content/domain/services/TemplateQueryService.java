package upc.edu.artcollab.api.content.domain.services;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TemplateQueryService {
    Optional<Template> handle(GetTemplateByIdQuery query);
    List<Template> handle(GetAllTemplatesByGenreQuery query);
}

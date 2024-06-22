package upc.edu.artcollab.api.content.domain.services;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * The TemplateQueryService interface provides methods to handle queries related to the Template entity.
 * <p>
 *     This interface provides methods to handle queries related to the Template entity.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
public interface TemplateQueryService {
    Optional<Template> handle(GetTemplateByIdQuery query);
    List<Template> handle(GetAllTemplatesByGenreQuery query);
    List<Template> handle(GetAllTemplatesQuery query);
}

package upc.edu.artcollab.api.content.interfaces.rest.transform;

import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.interfaces.rest.resources.TemplateResource;

public class TemplateResourceFromEntityAssembler {

    public static TemplateResource toResourceFromEntity(Template entity) {
        return new TemplateResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getType(), entity.getImgURL(), entity.getViews(), entity.getLikes(), entity.getGenre(), entity.getTemplateState_Id(), entity.getTemplateHistory_Id(), entity.getPortfolio_Id());
    }
}

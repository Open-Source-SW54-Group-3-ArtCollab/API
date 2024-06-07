package upc.edu.artcollab.api.comment.interfaces.rest.transform;

import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.FavoriteCommentResource;

public class FavoriteCommentResourceFromEntityAssembler {
    public static FavoriteCommentResource toResourceFromEntity(FavoriteComment entity) {
        return new FavoriteCommentResource(entity.getCommentid(), entity.getName(), entity.getImage(), entity.getContent(), entity.getRanklvl(), entity.getLikes(), entity.getDislikes());
    }
}

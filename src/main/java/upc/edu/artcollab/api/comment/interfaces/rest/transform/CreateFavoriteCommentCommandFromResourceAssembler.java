package upc.edu.artcollab.api.comment.interfaces.rest.transform;

import upc.edu.artcollab.api.comment.domain.model.commands.CreateFavoriteCommentCommand;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.CreateFavoriteCommentResource;

public class CreateFavoriteCommentCommandFromResourceAssembler {
    public static CreateFavoriteCommentCommand toCommandFromResource(CreateFavoriteCommentResource resource) {
        return new CreateFavoriteCommentCommand(resource.name(), resource.image(), resource.content(), resource.ranklvl(), resource.likes(), resource.dislikes());
    }
}

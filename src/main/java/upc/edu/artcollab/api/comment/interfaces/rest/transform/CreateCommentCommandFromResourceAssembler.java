package upc.edu.artcollab.api.comment.interfaces.rest.transform;

import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.CreateCommentResource;

public class CreateCommentCommandFromResourceAssembler {
    public static CreateCommentCommand toCommandFromResource(CreateCommentResource resource) {
        return new CreateCommentCommand( resource.content());
    }
}

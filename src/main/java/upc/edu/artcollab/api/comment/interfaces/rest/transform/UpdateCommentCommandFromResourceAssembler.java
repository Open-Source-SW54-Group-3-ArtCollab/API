package upc.edu.artcollab.api.comment.interfaces.rest.transform;

import upc.edu.artcollab.api.comment.domain.model.commands.UpdateCommentCommand;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.UpdateCommentResource;

public class UpdateCommentCommandFromResourceAssembler {
    public static UpdateCommentCommand toCommandFromResource(Long id,UpdateCommentResource updateCommentResource) {
        return new UpdateCommentCommand(id,updateCommentResource.content());
    }
}

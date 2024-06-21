package upc.edu.artcollab.api.comment.interfaces.rest.transform;

import upc.edu.artcollab.api.comment.domain.model.commands.DeleteCommentCommand;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.DeleteCommentResource;

public class DeleteCommentCommandFromResourceAssembler {
    public static DeleteCommentCommand toCommandFromResource(DeleteCommentResource deleteCommentResource){
        return new DeleteCommentCommand(deleteCommentResource.id());
    }
}

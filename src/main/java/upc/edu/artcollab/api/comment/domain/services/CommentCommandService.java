package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.DeleteCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.UpdateCommentCommand;

import java.util.Optional;

/**
 * Interface for the CommentCommandService
 * This interface is used to handle the commands for the Comment entity
 * The handle(CreateCommentCommand command) method creates a new Comment object and saves it to the database
 * The handle(DeleteCommentCommand command) method deletes a Comment object from the database
 * The handle(UpdateCommentCommand command) method updates a Comment object in the database
 */
public interface CommentCommandService {
    Optional<Comment> handle(CreateCommentCommand command);
    Optional<Comment> handle(DeleteCommentCommand command);
    Optional<Comment> handle(UpdateCommentCommand command);
}

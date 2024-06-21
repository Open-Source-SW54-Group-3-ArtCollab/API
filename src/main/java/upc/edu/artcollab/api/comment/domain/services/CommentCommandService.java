package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.DeleteCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.UpdateCommentCommand;

import java.util.Optional;

public interface CommentCommandService {
    Optional<Comment> handle(CreateCommentCommand command);
    Optional<Comment> handle(DeleteCommentCommand command);
    Optional<Comment> handle(UpdateCommentCommand command);
}
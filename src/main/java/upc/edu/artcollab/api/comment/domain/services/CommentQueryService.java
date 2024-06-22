package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllComments;
import upc.edu.artcollab.api.comment.domain.model.queries.GetCommentByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the CommentQueryService
 * This interface is used to handle the queries for the Comment entity
 * The handle(GetCommentByIdQuery query) method gets a Comment object by its id
 * The handle(GetAllComments query) method gets all Comment objects
 */

public interface CommentQueryService {

    Optional<Comment> handle(GetCommentByIdQuery query);

    List<Comment> handle(GetAllComments query);
}

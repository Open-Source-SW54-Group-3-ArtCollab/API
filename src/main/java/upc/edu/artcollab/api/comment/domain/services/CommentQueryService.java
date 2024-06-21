package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllComments;
import upc.edu.artcollab.api.comment.domain.model.queries.GetCommentByIdQuery;

import java.util.List;
import java.util.Optional;


public interface CommentQueryService {

    Optional<Comment> handle(GetCommentByIdQuery query);

    List<Comment> handle(GetAllComments query);
}

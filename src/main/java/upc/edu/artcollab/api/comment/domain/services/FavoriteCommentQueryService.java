package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllFavoriteCommentByName;
import upc.edu.artcollab.api.comment.domain.model.queries.GetFavoriteCommentByIdQuery;

import java.util.List;
import java.util.Optional;


public interface FavoriteCommentQueryService {
    List<FavoriteComment> handle(GetAllFavoriteCommentByName query);

    Optional<FavoriteComment> handle(GetFavoriteCommentByIdQuery query);

    List<FavoriteComment> getAllFavoriteComments();
}

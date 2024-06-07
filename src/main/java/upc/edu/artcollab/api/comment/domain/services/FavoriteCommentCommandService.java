package upc.edu.artcollab.api.comment.domain.services;

import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateFavoriteCommentCommand;

import java.util.Optional;

public interface FavoriteCommentCommandService {
    Optional<FavoriteComment> handle(CreateFavoriteCommentCommand command);
}

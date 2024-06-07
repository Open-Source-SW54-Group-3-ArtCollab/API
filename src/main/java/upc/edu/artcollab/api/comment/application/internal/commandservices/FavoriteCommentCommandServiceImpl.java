package upc.edu.artcollab.api.comment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateFavoriteCommentCommand;
import upc.edu.artcollab.api.comment.domain.services.FavoriteCommentCommandService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.FavoriteCommentRepository;

import java.util.Optional;

@Service
public class FavoriteCommentCommandServiceImpl implements FavoriteCommentCommandService {

    private final FavoriteCommentRepository favoriteCommentRepository;

    public FavoriteCommentCommandServiceImpl(FavoriteCommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }

    @Override
    public Optional<FavoriteComment> handle(CreateFavoriteCommentCommand command) {
        if (favoriteCommentRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Favorite comment with same name already exists");
        }

        var favoriteComment = new FavoriteComment(command);
        var createdFavoriteComment = favoriteCommentRepository.save(favoriteComment);

        return Optional.of(createdFavoriteComment);

    }
}

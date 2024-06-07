package upc.edu.artcollab.api.comment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllFavoriteCommentByName;
import upc.edu.artcollab.api.comment.domain.model.queries.GetFavoriteCommentByIdQuery;
import upc.edu.artcollab.api.comment.domain.services.FavoriteCommentQueryService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.FavoriteCommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteCommentQueryServiceImpl implements FavoriteCommentQueryService {

    private final FavoriteCommentRepository favoriteCommentRepository;

    public FavoriteCommentQueryServiceImpl(FavoriteCommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }
    @Override
    public List<FavoriteComment> handle(GetAllFavoriteCommentByName query) {
        return favoriteCommentRepository.findByName(query.name());
    }

    @Override
    public Optional<FavoriteComment> handle(GetFavoriteCommentByIdQuery query) {
        return favoriteCommentRepository.findById(query.commentId());
    }

    @Override
    public List<FavoriteComment> getAllFavoriteComments() {
        return favoriteCommentRepository.findAll();
    }
}

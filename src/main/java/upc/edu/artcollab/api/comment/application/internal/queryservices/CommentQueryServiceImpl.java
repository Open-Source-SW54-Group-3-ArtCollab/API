package upc.edu.artcollab.api.comment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllComments;
import upc.edu.artcollab.api.comment.domain.model.queries.GetCommentByIdQuery;
import upc.edu.artcollab.api.comment.domain.services.CommentQueryService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository favoriteCommentRepository;

    public CommentQueryServiceImpl(CommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }


    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return favoriteCommentRepository.findById(query.commentId());
    }

    @Override
    public List<Comment> handle(GetAllComments query) {
        return  favoriteCommentRepository.findAllComments();
    }

}

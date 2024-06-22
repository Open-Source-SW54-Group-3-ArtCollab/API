package upc.edu.artcollab.api.comment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllComments;
import upc.edu.artcollab.api.comment.domain.model.queries.GetCommentByIdQuery;
import upc.edu.artcollab.api.comment.domain.services.CommentQueryService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.CommentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CommentQueryService
 * This class is used to handle the queries for the Comment entity
 * <p>
 *     This class is annotated with @Service to indicate that it is a Spring service
 * </p>
 * @see CommentQueryService
 * @see GetCommentByIdQuery
 * @see GetAllComments
 * @version 1.0
 * @author Juan Alejandro Cuadros Rodriguez - u20221a359
 */
@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository favoriteCommentRepository;

    public CommentQueryServiceImpl(CommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }

    /**
     * Handles the query to get a comment by its id
     * @param query the query to get a comment by its id
     * @return the comment if found
     */
    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return favoriteCommentRepository.findById(query.commentId());
    }

    /**
     * Handles the query to get all comments
     * @param query the query to get all comments
     * @return the list of comments
     */
    @Override
    public List<Comment> handle(GetAllComments query) {
        return  favoriteCommentRepository.findAllComments();
    }

}

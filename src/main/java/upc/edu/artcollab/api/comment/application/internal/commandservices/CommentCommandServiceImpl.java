package upc.edu.artcollab.api.comment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.DeleteCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.UpdateCommentCommand;
import upc.edu.artcollab.api.comment.domain.services.CommentCommandService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.CommentRepository;

import java.util.Optional;

/**
 * Implementation of the CommentCommandService
 * This class is used to handle the commands for the Comment entity
 * <p>
 *     This class is annotated with @Service to indicate that it is a Spring service
 * </p>
 * @see CommentCommandService
 * @version 1.0
 * @author Juan Alejandro Cuadros Rodriguez - u20221a359
 */
@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository favoriteCommentRepository;

    public CommentCommandServiceImpl(CommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }

    /**
     * Handles the command to create a comment
     * @param command the command to create a comment
     * @return the created comment
     */
    @Override
    public Optional<Comment> handle(CreateCommentCommand command) {
        var favoriteComment = new Comment(command);
        var createdFavoriteComment = favoriteCommentRepository.save(favoriteComment);
        return Optional.of(createdFavoriteComment);

    }

    /**
     * Handles the command to delete a comment
     * @param command the command to delete a comment
     * @return the deleted comment
     */
    @Override
    public Optional<Comment> handle(DeleteCommentCommand command) {
        var favoriteComment = favoriteCommentRepository.findById(command.id());
        favoriteComment.ifPresent(favoriteCommentRepository::delete);
        return favoriteComment;
    }

    /**
     * Handles the command to update a comment
     * @param command the command to update a comment
     * @return the updated comment
     */
    @Override
    public Optional<Comment> handle(UpdateCommentCommand command) {
        var comment = favoriteCommentRepository.findById(command.id());
        if(comment.isPresent()){
            comment.get().setContent(command.content());
        }
        return comment;
    }

}

package upc.edu.artcollab.api.comment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.DeleteCommentCommand;
import upc.edu.artcollab.api.comment.domain.model.commands.UpdateCommentCommand;
import upc.edu.artcollab.api.comment.domain.services.CommentCommandService;
import upc.edu.artcollab.api.comment.infrastructure.persistance.jpa.CommentRepository;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository favoriteCommentRepository;

    public CommentCommandServiceImpl(CommentRepository favoriteCommentRepository) {
        this.favoriteCommentRepository = favoriteCommentRepository;
    }

    @Override
    public Optional<Comment> handle(CreateCommentCommand command) {
        var favoriteComment = new Comment(command);
        var createdFavoriteComment = favoriteCommentRepository.save(favoriteComment);
        return Optional.of(createdFavoriteComment);

    }

    @Override
    public Optional<Comment> handle(DeleteCommentCommand command) {
        var favoriteComment = favoriteCommentRepository.findById(command.id());
        favoriteComment.ifPresent(favoriteCommentRepository::delete);
        return favoriteComment;
    }

    @Override
    public Optional<Comment> handle(UpdateCommentCommand command) {
        var comment = favoriteCommentRepository.findById(command.id());
        if(comment.isPresent()){
            comment.get().setContent(command.content());
        }
        return comment;
    }

}

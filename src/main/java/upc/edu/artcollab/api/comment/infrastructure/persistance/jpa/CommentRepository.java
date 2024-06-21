package upc.edu.artcollab.api.comment.infrastructure.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c")
    List<Comment> findAllComments();
}

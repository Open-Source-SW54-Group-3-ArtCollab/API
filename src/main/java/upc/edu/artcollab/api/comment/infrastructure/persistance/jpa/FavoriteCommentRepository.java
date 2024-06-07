package upc.edu.artcollab.api.comment.infrastructure.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;

import java.util.List;

public interface FavoriteCommentRepository extends JpaRepository<FavoriteComment, Long> {
    List<FavoriteComment> findByName(String name);

    boolean existsByName(String name);
}

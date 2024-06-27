package upc.edu.artcollab.api.users.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Reader> findByEmailAndPassword(String email, String password);
    @Query("SELECT r FROM Reader r ")
    List<Reader> findAllReaders();
}

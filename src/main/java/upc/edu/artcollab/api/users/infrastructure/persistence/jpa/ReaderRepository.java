package upc.edu.artcollab.api.users.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Reader> findByEmailAndPassword(String email, String password);
    boolean existsByType(UserTypes type);
}

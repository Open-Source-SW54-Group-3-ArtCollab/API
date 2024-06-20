package upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.artcollab.api.content.domain.model.entities.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
}

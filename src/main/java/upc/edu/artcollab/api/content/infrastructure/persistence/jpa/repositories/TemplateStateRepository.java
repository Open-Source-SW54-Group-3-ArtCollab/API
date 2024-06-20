package upc.edu.artcollab.api.content.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateState;

public interface TemplateStateRepository extends JpaRepository<TemplateState, Integer> {

}

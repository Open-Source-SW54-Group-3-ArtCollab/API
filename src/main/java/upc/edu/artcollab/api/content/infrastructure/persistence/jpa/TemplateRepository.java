package upc.edu.artcollab.api.content.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;

import java.util.List;

/**
 * Repository interface for managing Template entities.
 *
 * <p>This repository provides methods to perform CRUD operations on Template entities, as well as custom queries
 * to find templates by their genre, description, and image URL.</p>
 */
public interface TemplateRepository extends JpaRepository<Template, Integer> {

    /**
     * Finds all Template entities by the specified genre.
     *
     * @param genre the genre
     * @return a list of Template entities matching the specified genre
     */
    List<Template> findAllByGenre(String genre);

    /**
     * Checks if a Template entity exists by the specified description.
     *
     * @param description the description
     * @return true if a Template entity exists with the specified description, false otherwise
     */
    boolean existsByDescription(String description);

    /**
     * Checks if a Template entity exists by the specified image URL.
     *
     * @param imgURL the image URL
     * @return true if a Template entity exists with the specified image URL, false otherwise
     */
    boolean existsByImgURL(String imgURL);
}

package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import upc.edu.artcollab.api.shared.domain.model.entities.AuditableModel;

/**
 * The Portfolio class is an entity model.
 * <p>
 *     This class represents the Portfolio entity. It contains the attributes of a Portfolio.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@Getter
@Entity
public class Portfolio extends AuditableModel {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    /**
     * Constructor method for the Portfolio class.
     * @param id The id of the Portfolio.
     * @param title The title of the Portfolio.
     * @param description The description of the Portfolio.
     * @param quantity The quantity of the Portfolio.
     */
    public Portfolio(Integer id, String title, String description, Integer quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Default constructor method for the Portfolio class.
     */
    public Portfolio() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.quantity = 0;
    }
}

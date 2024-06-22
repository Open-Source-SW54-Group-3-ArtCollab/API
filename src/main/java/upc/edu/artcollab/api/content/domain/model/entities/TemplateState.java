package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * The TemplateState class is an entity model.
 * <p>
 *     This class represents the TemplateState entity. It contains the attributes of a TemplateState.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@Entity
@Getter
public class TemplateState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean flag;

    /**
     * Constructor with parameters.
     * @param flag The flag of the TemplateState.
     */
    public TemplateState(Boolean flag) {
        this.flag = flag;
    }

    /**
     * Default constructor.
     */
    public TemplateState() {
        this.flag = false;
    }
}

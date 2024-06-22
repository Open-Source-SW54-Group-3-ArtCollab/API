package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * The TemplateHistory class is an entity model.
 * <p>
 *     This class represents the TemplateHistory entity. It contains the attributes of a TemplateHistory.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
public class TemplateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    private Date modified_at;

    @Column(nullable = true)
    private Date deleted_At;

    /**
     * Constructor method for TemplateHistory.
     * @param modified_at Date of the last modification.
     * @param deleted_At Date of deletion.
     */
    public TemplateHistory(Date modified_at ,Date deleted_At) {
        this.modified_at = modified_at;
        this.deleted_At = deleted_At;
    }

    /**
     * Empty constructor method for TemplateHistory.
     */
    public TemplateHistory() {
    }
}

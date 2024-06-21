package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

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

    public TemplateHistory(Date modified_at ,Date deleted_At) {
        this.modified_at = modified_at;
        this.deleted_At = deleted_At;
    }

    public TemplateHistory() {
    }
}

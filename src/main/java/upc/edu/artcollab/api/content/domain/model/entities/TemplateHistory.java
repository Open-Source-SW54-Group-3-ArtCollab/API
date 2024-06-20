package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

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

    public TemplateHistory(Date deleted_At) {
        this.deleted_At = deleted_At;
    }

    public TemplateHistory() {
    }
}

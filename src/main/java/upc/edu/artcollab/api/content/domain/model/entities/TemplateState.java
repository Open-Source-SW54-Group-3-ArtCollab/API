package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TemplateState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean flag;

    public TemplateState(Boolean flag) {
        this.flag = flag;
    }

    public TemplateState() {
        this.flag = false;
    }
}

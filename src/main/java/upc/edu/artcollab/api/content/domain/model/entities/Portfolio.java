package upc.edu.artcollab.api.content.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import upc.edu.artcollab.api.shared.domain.model.entities.AuditableModel;

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

    public Portfolio(Integer id, String title, String description, Integer quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
    }

    public Portfolio() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.quantity = 0;
    }
}

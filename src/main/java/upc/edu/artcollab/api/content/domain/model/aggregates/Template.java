package upc.edu.artcollab.api.content.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.entities.Portfolio;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateHistory;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateState;
import upc.edu.artcollab.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

/**
 * The Template class is an aggregate root entity model.
 * <p>
 *     This class represents the Template aggregate root. It contains the attributes of a Template.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@Getter
@Setter
@Entity
public class Template extends AuditableAbstractAggregateRoot<Template> {

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date date_publish;

    @NotBlank
    @Column(nullable = false, updatable = false)
    private String type;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String imgURL;

    @Column(nullable = false)
    private Integer views;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "templateState_Id", nullable = false)
    private TemplateState templateState;

    @OneToOne
    @JoinColumn(name = "templateHistory_Id", nullable = false)
    private TemplateHistory templateHistory;

    @ManyToOne
    @JoinColumn(name = "portfolio_Id", nullable = false)
    private Portfolio portfolio;

    /**
     * Protected no-arg constructor for JPA.
     */
    protected Template() {}

    /**
     * Constructor.
     * It creates a new Template instance.
     *
     * @param command the CreateTemplateCommand command containing the details of the template
     */
    public Template(CreateTemplateCommand command, TemplateState templateState, TemplateHistory templateHistory, Portfolio portfolio) {
        this.title = command.title();
        this.description = command.description();
        this.type = command.type();
        this.imgURL = command.imgURL();
        this.views = command.views();
        this.likes = command.likes();
        this.genre = command.genre();
        this.templateState = templateState;
        this.templateHistory = templateHistory;
        this.portfolio = portfolio;
    }
}

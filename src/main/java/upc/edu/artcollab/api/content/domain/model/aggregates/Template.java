package upc.edu.artcollab.api.content.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.entities.Portfolio;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateHistory;
import upc.edu.artcollab.api.content.domain.model.entities.TemplateState;
import upc.edu.artcollab.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
@Entity
public class Template extends AuditableAbstractAggregateRoot<Template> {

    /** The title of the template. */
    @NotBlank
    @Column(nullable = false)
    private String title;

    /** The description of the template. */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String description;

    /** The date and time when the template was published. */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date date_publish;

    /** The type of the template. */
    @NotBlank
    @Column(nullable = false, updatable = false)
    private String type;

    /** The URL of the thumbnail of the template. */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String imgURL;

    /** The number of views of the template. */
    @Column(nullable = false)
    private Integer views;

    /** The number of likes of the template. */
    @Column(nullable = false)
    private Integer likes;

    /** The genre of the template. */
    @Column(nullable = false)
    private String genre;

    /** The identifier of the state of the template. */
    @ManyToOne
    @JoinColumn(name = "templateState_Id", nullable = false)
    private TemplateState templateState;

    /** The identifier of the history of the template. */
    @OneToOne
    @JoinColumn(name = "templateHistory_Id", nullable = false)
    private TemplateHistory templateHistory;

    /** The identifier of the portfolio of the template. */
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

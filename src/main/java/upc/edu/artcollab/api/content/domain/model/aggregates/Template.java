package upc.edu.artcollab.api.content.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import upc.edu.artcollab.api.content.domain.model.commands.CreateTemplateCommand;
import upc.edu.artcollab.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
@Entity
public class Template extends AuditableAbstractAggregateRoot<Template> {

    /** The unique identifier of the favorite source. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    /** The title of the template. */
    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    /** The description of the template. */
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String description;

    /** The date and time when the template was published. */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date date_publish;

    /** The type of the template. */
    @Column(nullable = false, updatable = false)
    @Getter
    @Setter
    private String type;

    /** The URL of the thumbnail of the template. */
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String imgURL;

    /** The number of views of the template. */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer views;

    /** The number of likes of the template. */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer likes;

    /** The genre of the template. */
    @Column(nullable = false)
    @Getter
    @Setter
    private String genre;

    /** The identifier of the state of the template. */
    @Column(nullable = false)
    @Getter
    private Integer templateState_Id;

    /** The identifier of the history of the template. */
    @Column(nullable = false)
    @Getter
    private Integer templateHistory_Id;

    /** The identifier of the portfolio of the template. */
    @Column(nullable = false)
    @Getter
    private Integer portfolio_Id;

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
    public Template(CreateTemplateCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.type = command.type();
        this.imgURL = command.imgURL();
        this.views = command.views();
        this.likes = command.likes();
        this.genre = command.genre();
        this.templateState_Id = command.templateState_Id();
        this.templateHistory_Id = command.templateHistory_Id();
        this.portfolio_Id = command.portfolio_Id();
    }
}

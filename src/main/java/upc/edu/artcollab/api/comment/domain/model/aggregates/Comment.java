package upc.edu.artcollab.api.comment.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateCommentCommand;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbstractAggregateRoot<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String content;
    protected Comment() {}

    public Comment(CreateCommentCommand command) {
        this.content = command.content();
    }
}

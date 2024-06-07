package upc.edu.artcollab.api.comment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.comment.domain.model.commands.CreateFavoriteCommentCommand;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteComment extends AbstractAggregateRoot<FavoriteComment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long commentid;
    @Column(nullable = false)
    @Getter
    private String name;
    @Column(nullable = false)
    @Getter
    private String image;
    @Column(nullable = false)
    @Getter
    private String content;
    @Column(nullable = false)
    @Getter
    private String ranklvl;
    @Column(nullable = false)
    @Getter
    private Integer likes;
    @Column(nullable = false)
    @Getter
    private Integer dislikes;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
    protected FavoriteComment() {}

    public FavoriteComment(CreateFavoriteCommentCommand command) {
        this.name = command.name();
        this.image = command.image();
        this.content = command.content();
        this.ranklvl = command.ranklvl();
        this.likes = command.likes();
        this.dislikes = command.dislikes();
    }
}

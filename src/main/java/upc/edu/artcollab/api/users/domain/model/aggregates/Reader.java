package upc.edu.artcollab.api.users.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;

import java.util.Date;

/**
 * Reader entity
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reader extends AbstractAggregateRoot<Reader> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false, unique = true)
    @Getter
    private String username;

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String type;

    @Column(nullable = false)
    @Getter
    private String imgUrl;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createdAt = new Date();

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt = new Date();

    protected Reader() {
    }

    public Reader(CreateReaderCommand command) {
        this.name = command.name();
        this.username = command.username();
        this.email = command.email();
        this.password = command.password();
        this.type = command.type() != null ? command.type() : "reader";
        this.imgUrl = command.imgUrl() != null ? command.imgUrl() : "https://randomuser.me/api/portraits/thumb/men/15.jpg";

    }

}

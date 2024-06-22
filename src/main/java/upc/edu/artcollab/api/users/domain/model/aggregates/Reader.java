package upc.edu.artcollab.api.users.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.shared.infrastructure.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;

/**
 * Reader aggregate root.
 * <p>
 *     This class represents a reader in the domain model.
 * </p>
 * @author Gustavo Huilca Chipana
 */

/**
 * Reader entity
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reader extends AuditableAbstractAggregateRoot<Reader> {

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

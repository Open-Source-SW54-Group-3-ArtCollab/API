package upc.edu.artcollab.api.users.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.shared.infrastructure.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.artcollab.api.users.domain.model.commands.CreateReaderCommand;
import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

/**
 * Reader aggregate root.
 * <p>
 *     This class represents a reader in the domain model.
 * </p>
 * @version 1.0
 * @autor Gustavo Huilca Chipana - u202213983
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reader extends AuditableAbstractAggregateRoot<Reader> {

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypes type;

    @Column(nullable = false)
    private String imgUrl;


    public Reader() {
    }


    public Reader(CreateReaderCommand command) {
        this.name = command.name();
        this.username = command.username();
        this.email = command.email();
        this.password = command.password();
        this.type = command.type();
        this.imgUrl = command.imgUrl() != null ? command.imgUrl() : "https://randomuser.me/api/portraits/thumb/men/15.jpg";

    }

}

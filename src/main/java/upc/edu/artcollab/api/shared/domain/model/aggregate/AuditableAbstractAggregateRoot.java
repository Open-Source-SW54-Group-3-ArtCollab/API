/**
 * @summary
 * This class represents an abstract aggregate root that is auditable.
 * It extends AbstractAggregateRoot.
 * It is abstract.
 */

package upc.edu.artcollab.api.shared.domain.model.aggregate;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    /**
     * @summary id field
     * @description This field is the primary key of the entity.
     * It is automatically generated by the database.
     * It is of type long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
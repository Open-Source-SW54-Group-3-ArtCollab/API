/**
 *
 * @summary
 * Created subscription aggregate  and implements their attributes
 */


package upc.edu.artcollab.api.monetization.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;


/**
 * @summary
 * used lombok annotations for aggregate attributes
 */
@Entity
public class Subscription extends AbstractAggregateRoot<Subscription> {

    /**
     * @summary
     * declare id and this have auto generated value
     */

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long id;


    /**
     * @summary
     * declare getters and setters for isActive attribute
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Boolean isActive;

    protected Subscription() {

    }

}

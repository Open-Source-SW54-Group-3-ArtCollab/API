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
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;


/**
 * @summary
 * used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
 * and used jakarta persistence annotations for entity and id
 * and used getter and setter for isActive attribute
 *
 */
@Entity
public class Subscription extends AbstractAggregateRoot<Subscription> {

    /**
     * @summary
     * declare id and this have auto generated value
     * and used lombok getter for id
     *
     */

    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    /**
     * @summary
     * declare getters and setters for isActive attribute
     *  and used lombok annotations for getter and setter
     *  and used jakarta persistence annotations for column
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private boolean isActive;

    protected Subscription() {
        /**
         * @summary
         * default constructor
         * used for hibernate
         * and used lombok annotations for no args constructor
         * and used jakarta persistence annotations for entity
         * and used lombok annotations for no args constructor
         */
    }

    public Subscription(CreateSubscriptionCommand command) {
        this.isActive = command.isActive();
    }

}

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
import upc.edu.artcollab.api.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;


/**
 *  Subscription aggregate root
 *  <p>
 *      This class is the aggregate root for the Subscription aggregate.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 *
 */
@Entity
public class Subscription extends AuditableAbstractAggregateRoot<Subscription> {


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

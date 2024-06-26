/**
 * @summary
 * Created commision aggregate  and implements their attributes
 *  and used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
 *  and used jakarta persistence annotations for entity and id
 *  and used getter for all attributes
 */


package upc.edu.artcollab.api.monetization.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

/**
 * @summary
 * used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
 * and used jakarta persistence annotations for entity and id
 * and used getter for all attributes
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Commision extends AuditableAbstractAggregateRoot<Commision> {


    /**
     * @summary
     * declare amount and used lombok getter for amount
     *
     */


    @Column(nullable = false)
    @Getter
    @Setter
    private double amount;

    /**
     * @summary
     * declare content and used lombok getter for content
     *
     */


    @Column(nullable = false)
    @Getter
    @Setter
    private String content;

    /**
     * @summary
     * declare date and used lombok getter for date
     * and used spring annotations for created date
     *
     */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;

    protected Commision() {
        /**
         * @summary
         * default constructor
         * and used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
         * and used jakarta persistence annotations for entity and id
         * and used getter for all attributes
         * and used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
         */
    }

    public Commision (CreateCommisionCommand command){
        /**
         * @summary
         * constructor with command
         * and used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
         * and used jakarta persistence annotations for entity and id
         * and used getter for all attributes
         * and used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
         */
        this.amount = command.amount();
        this.content = command.content();
        this.date = LocalDateTime.now();
    }

}

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
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

/**
 * @summary
 * used lombok annotations for aggregate attributes and extends AbstractAggregateRoot for aggregate root
 * and used jakarta persistence annotations for entity and id
 * and used getter for all attributes
 */

@Entity
public class Commision extends AbstractAggregateRoot<Commision> {

    /**
     * @summary
     * declare id and this have auto generated value
     * and used lombok getter for id
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /**
     * @summary
     * declare amount and used lombok getter for amount
     *
     */


    @Column(nullable = false)
    @Getter
    private double amount;

    /**
     * @summary
     * declare content and used lombok getter for content
     *
     */


    @Column(nullable = false)
    @Getter
    private String content;

    /**
     * @summary
     * declare date and used lombok getter for date
     *
     */

    @Column(nullable = false)
    @Getter
    private Date date;

    protected Commision() {

    }




}

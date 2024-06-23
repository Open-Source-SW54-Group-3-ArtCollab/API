package upc.edu.artcollab.api.monetization.domain.model.aggregates;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import jakarta.persistence.Column;
import jdk.jfr.Description;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


/**
 * Payment aggregate root
 * <p>
 *     This class is the aggregate root for the payment entity
 * </p>
 * @author Samira Alvarez Araguache
 * @version 1.0
 */
public class Payment {

    /**
     * @summary
     * declare amount and used lombok getter for amount
     *
     */
    @Getter
    private Amount amount;

    /**
     * @summary
     * declare currency and used lombok getter for currency
     *
     */
    @Getter
    private Currency currency;

    /**
     * @summary
     * declare description and used lombok getter for description
     *
     */
    @Getter
    private String description;

    /**
     * @summary
     * declare status and used lombok getter for status
     *
     */
    @Getter
    private String status;

    /**
     * @summary
     * declare date and used lombok getter for date
     *
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;

    /**
     * @summary
     * default constructor
     * used for hibernate
     * and used lombok annotations for no args constructor
     * and used jakarta persistence annotations for entity
     * and used lombok annotations for no args constructor
     */
    protected Payment() {
    }



    public Payment(Amount amount, Currency currency, String description, String status) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.status = status;
    }

}

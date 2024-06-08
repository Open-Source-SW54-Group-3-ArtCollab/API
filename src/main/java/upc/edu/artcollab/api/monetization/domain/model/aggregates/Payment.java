package upc.edu.artcollab.api.monetization.domain.model.aggregates;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import jakarta.persistence.Column;
import jdk.jfr.Description;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Payment {

    @Getter
    private Amount amount;

    @Getter
    private Currency currency;

    @Getter
    private String description;

    @Getter
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;
    protected Payment() {
    }

    public Payment(Amount amount, Currency currency, String description, String status) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.status = status;
    }

}

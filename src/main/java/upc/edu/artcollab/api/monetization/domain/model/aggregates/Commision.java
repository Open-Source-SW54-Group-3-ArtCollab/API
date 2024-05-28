package upc.edu.artcollab.api.monetization.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Entity
public class Commision extends AbstractAggregateRoot<Commision> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;


    @Column(nullable = false)
    @Getter
    private double amount;


    @Column(nullable = false)
    @Getter
    private String content;

    @Column(nullable = false)
    @Getter
    private Date date;

    protected Commision() {

    }




}

package upc.edu.artcollab.api.monetization.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionsByAmountGreatherThanQuery;
import upc.edu.artcollab.api.monetization.domain.services.CommisionQueryService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.CommisionRepository;

import java.util.List;
import java.util.Optional;

/**
 * CommisionQueryServiceImpl
 * <p>
 * This class implements the CommisionQueryService interface and provides the implementation for the
 * methods declared in the interface.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class CommisionQueryServiceImpl implements CommisionQueryService {

    private final CommisionRepository commisionRepository;

    /**
     * Constructor for CommisionQueryServiceImpl.
     *
     * @param commisionRepository the repository for Commision
     */
    public CommisionQueryServiceImpl(CommisionRepository commisionRepository) {
        this.commisionRepository = commisionRepository;
    }

    /**
     * Handles the query to get a Commision by its ID.
     *
     * @param query the query containing the ID of the Commision
     * @return the Commision with the provided ID, if it exists
     */
    @Override
    public Optional<Commision> handle(GetCommisionByIdQuery query) {
        return commisionRepository.findById(query.id());
    }

    /**
     * Handles the query to get all Commisions with an amount greater than a specified value.
     *
     * @param query the query containing the amount
     * @return a list of Commisions with an amount greater than the specified value
     */
    @Override
    public List<Commision> handle(GetCommisionsByAmountGreatherThanQuery query) {
        return commisionRepository.findCommisionByAmountGreaterThan(query.amount());
    }

    /**
     * Gets all Commisions.
     *
     * @return a list of all Commisions
     */
    @Override
    public List<Commision> getAll() {
        return commisionRepository.getAll();
    }
}
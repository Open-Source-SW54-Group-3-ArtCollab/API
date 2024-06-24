package upc.edu.artcollab.api.monetization.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetAllSubscriptionsActiveQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetSubscriptionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionQueryService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.SubscriptionRepository;

import java.util.List;
import java.util.Optional;

/**
 * SubscriptionQueryServiceImpl
 * <p>
 * This class implements the SubscriptionQueryService interface and provides the implementation for the
 * methods declared in the interface.
 * </p>
 * @author U202213375 Italo Luna Capuñay
 * @version 1.0
 */
@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructor for SubscriptionQueryServiceImpl.
     *
     * @param subscriptionRepository the repository for Subscription
     */
    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Gets all Subscriptions.
     *
     * @return a list of all Subscriptions
     */
    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.getAll();
    }

    /**
     * Handles the query to get a Subscription by its ID.
     *
     * @param query the query containing the ID of the Subscription
     * @return the Subscription with the provided ID, if it exists
     */
    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.id());
    }

    /**
     * Handles the query to get all active Subscriptions.
     *
     * @param query the query containing the active status
     * @return a list of active Subscriptions
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsActiveQuery query) {
        return subscriptionRepository.findSubscriptionByIsActive(query.isActive());
    }
}
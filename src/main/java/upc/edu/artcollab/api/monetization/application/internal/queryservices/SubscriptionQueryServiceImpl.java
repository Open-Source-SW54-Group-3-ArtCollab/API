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
 *  SubscriptionQueryServiceImpl implements SubscriptionQueryService
 *  <p>
 *      This class is responsible for handling the queries related to Subscription
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.getAll();
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.id());
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionsActiveQuery query) {
        return subscriptionRepository.findSubscriptionByIsActive(query.isActive());
    }
}

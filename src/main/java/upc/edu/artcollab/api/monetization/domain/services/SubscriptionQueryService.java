package upc.edu.artcollab.api.monetization.domain.services;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetAllSubscriptionsActiveQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 *  SubscriptionQueryService interface
 *  <p>
 *      This interface provides the methods to handle the queries related to the Subscription entity.
 *  </p>
 * @author  U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface SubscriptionQueryService {

    List<Subscription> getAll();
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
    List<Subscription> handle(GetAllSubscriptionsActiveQuery query);
}

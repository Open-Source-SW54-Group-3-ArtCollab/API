/**
 * @summary
 * This file contains the SubscriptionResourceFromEntityAssembler class, which is used to convert a Subscription entity to a SubscriptionResource.
 * This class is used to convert a Subscription entity to a SubscriptionResource.
 * The SubscriptionResourceFromEntityAssembler class is used to convert a Subscription entity to a SubscriptionResource.
 * The SubscriptionResource class is used to represent a Subscription entity in the REST API.
 * The Subscription entity is used to represent a subscription in the monetization module.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.transform;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {

    /**
     * @summary
     * This method is used to convert a Subscription entity to a SubscriptionResource.
     * @param subscription
     * The subscription entity to be converted.
     * @return
     * The SubscriptionResource object that represents the subscription entity.
     */

    public static SubscriptionResource fromEntity(Subscription subscription) {
        return new SubscriptionResource(subscription.getId(), subscription.isActive());
    }
}

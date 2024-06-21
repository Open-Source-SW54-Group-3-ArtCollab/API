/**
 * @summary
 * This file contains the interface SubscriptionCommandservice, which is used to define the methods that must be implemented by the SubscriptionCommandService class.
 * This interface defines the methods that must be implemented by the SubscriptionCommandService class.
 * The SubscriptionCommandService class is used to handle the commands related to the Subscription entity.
 */


package upc.edu.artcollab.api.monetization.domain.services;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {

    Optional<Subscription> handle(CreateSubscriptionCommand command);
    Optional<Subscription> handle(DeleteSubscriptionCommand command);
    Optional<Subscription> handle (Long id,UpdateSubscriptionCommand command);
}

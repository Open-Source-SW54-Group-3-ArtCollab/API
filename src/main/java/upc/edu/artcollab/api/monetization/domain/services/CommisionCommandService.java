/**
 * @summary
 * This interface defines the methods that the CommisionCommandService class must implement.
 * The CommisionCommandService class is responsible for handling the commands related to the Commision entity.
 * The methods defined in this interface are used to handle the creation of a new Commision command.
 */


package upc.edu.artcollab.api.monetization.domain.services;


import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;

import java.util.Optional;

public interface CommisionCommandService {

    Optional<Commision> handle(CreateCommisionCommand command);
    Optional<Commision> update(Commision commision);
    void delete(Commision commision);
}

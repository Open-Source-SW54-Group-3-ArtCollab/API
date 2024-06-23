/**
 * @summary
 * This interface defines the methods that the CommisionCommandService class must implement.
 * The CommisionCommandService class is responsible for handling the commands related to the Commision entity.
 * The methods defined in this interface are used to handle the creation of a new Commision command.
 */


package upc.edu.artcollab.api.monetization.domain.services;


import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateCommisionCommand;

import java.util.Optional;

/**
 * CommisionCommandService interface
 * <p>
 *     This interface defines the methods that the CommisionCommandService class must implement.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface CommisionCommandService {

    Optional<Commision> handle(CreateCommisionCommand command);
    Optional<Commision> handle(Long id,UpdateCommisionCommand command);
    Optional<Commision> handle(DeleteCommisionCommand command);
}

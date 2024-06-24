package upc.edu.artcollab.api.monetization.domain.model.commands;

/**
 * DeleteSubscriptionCommand
 * <p>
 * This record class encapsulates the command to delete a subscription. It contains the id of the subscription to be deleted.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record DeleteSubscriptionCommand(
        /**
         * The id of the subscription to be deleted.
         */
        long id) {
}
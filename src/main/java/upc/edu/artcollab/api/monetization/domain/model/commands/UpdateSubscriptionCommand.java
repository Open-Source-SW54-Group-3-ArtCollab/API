package upc.edu.artcollab.api.monetization.domain.model.commands;

/**
 * UpdateSubscriptionCommand
 * <p>
 * This record class encapsulates the command to update a subscription. It contains the id of the subscription to be updated,
 * and the new active status.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record UpdateSubscriptionCommand(
        /**
         * The id of the subscription to be updated.
         */
        long id,

        /**
         * The new active status for the subscription.
         */
        boolean isActive) {
}
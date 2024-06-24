package upc.edu.artcollab.api.monetization.domain.model.commands;

/**
 * UpdateCommisionCommand
 * <p>
 * This record class encapsulates the command to update a commission. It contains the id of the commission to be updated,
 * the new amount, and the new content.
 * </p>
 * @author U202213375 Italo Luna Capuñay
 * @version 1.0
 */
public record UpdateCommisionCommand(
        /**
         * The id of the commission to be updated.
         */
        long id,

        /**
         * The new amount for the commission.
         */
        double amount,

        /**
         * The new content for the commission.
         */
        String content) {
}
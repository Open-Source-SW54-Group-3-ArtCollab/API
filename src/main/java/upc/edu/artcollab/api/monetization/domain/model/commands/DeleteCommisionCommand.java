package upc.edu.artcollab.api.monetization.domain.model.commands;

/**
 * DeleteCommisionCommand
 * <p>
 * This record class encapsulates the command to delete a commission. It contains the id of the commission to be deleted.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record DeleteCommisionCommand(
        /**
         * The id of the commission to be deleted.
         */
        long id) {
}
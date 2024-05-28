/**
 * CreateCommisionCommand
 * @summary
 *  - This class is a record class that represents the command to create a commission.
 *  - It contains the amount and content of the commission.
 *  - It validates the amount and content to ensure they are greater than 0 and not null or empty.
 *  - This class is used to create a commission in the domain model.
 *  - This class is used in the Monetization API to create a commission.
 *  - This class is similar to the Subscription class in the Monetization domain model, which represents a subscription.
 *  - Both classes are used to create entities in the domain model and have attributes that are validated to ensure they are valid.
 */


package upc.edu.artcollab.api.monetization.domain.model.commands;

public record CreateCommisionCommand(double amount, String content) {

    /**
     *
     *  CreateCommisionCommand
     * @param amount
     *  - The amount of the commission.
     *  - It must be greater than 0.
     *  - It is validated in the constructor to ensure it is greater than 0.
     * @param content
     * - The content of the commission.
     * - It must not be null or empty.
     * - It is validated in the constructor to ensure it is not null or empty.
     */

    public CreateCommisionCommand {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount and content must be greater than 0");
        }

        if (content == null || content.isBlank() ) {
            throw new IllegalArgumentException("Content must not be null or empty");
        }
    }
}

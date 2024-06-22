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


}

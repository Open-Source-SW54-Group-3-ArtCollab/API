/**
 * @summary
 * CreateSubscriptionCommand.java
 * used lombok annotations for record class and used lombok annotations for constructor
 * This file contains the CreateSubscriptionCommand class, which is a record class that represents the command to create a subscription.
 * It contains the isActive attribute of the subscription.
 * It validates the isActive attribute to ensure it is not null.
 */


package upc.edu.artcollab.api.monetization.domain.model.commands;


public record CreateSubscriptionCommand(Boolean isActive) {
    /**
     * CreateSubscriptionCommand
     * @param isActive
     * - The isActive attribute of the subscription.
     * - It must not be null.
     * - It is validated in the constructor to ensure it is not null.
     * - This class is used to create a subscription in the domain model.
     * - This class is used in the Monetization API to create a subscription.
     */

    public CreateSubscriptionCommand{
        if(isActive == null){
            throw new IllegalArgumentException("isActive cannot be null");
        }
    }

}

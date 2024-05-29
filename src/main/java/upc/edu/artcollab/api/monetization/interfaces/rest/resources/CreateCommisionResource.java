/**
 * @summary
 * This file contains the CreateCommisionResource class, which is used to define the CreateCommisionResource class.
 * This class is used to define the CreateCommisionResource class.
 * The CreateCommisionResource class is used to define the CreateCommisionResource class.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.resources;

public record CreateCommisionResource(double amount, String content) {
    public CreateCommisionResource {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content must not be null or empty");
        }
    }
}

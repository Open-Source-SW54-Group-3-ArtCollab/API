/**
 * @summary
 * Declare the Content value object
 * Declare as record for immutability
 */

package upc.edu.artcollab.api.monetization.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Content(String content) {

    /**
     * @summary
     * Constructor for the Content value object
     * @param content
     *  This value object represents the content of commision
     * @return
     * Content
     */
    @JsonCreator
    public static Content of(String content) {
        return new Content(content);
    }

    /**
     * @summary
     * This method checks if the content is empty.
     * @param content
     * The content of commision
     */


    public Content {
        if (content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }
}

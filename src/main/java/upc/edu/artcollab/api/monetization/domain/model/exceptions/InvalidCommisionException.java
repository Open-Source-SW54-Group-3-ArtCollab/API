package upc.edu.artcollab.api.monetization.domain.model.exceptions;

/**
 * Exception thrown when a commision is invalid
 * <p>
 *      A commision is invalid if the amount is less than or equal to 0
 * </p>
 * @author  U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class InvalidCommisionException extends RuntimeException{
    public InvalidCommisionException(String message) {
        super(message);
    }
}

package upc.edu.artcollab.api.users.domain.model.exceptions;

/**
 * Exception thrown when an email already exists
 * <p>
 *     This exception is thrown when an email already exists in the system.
 * </p>
 * @version 1.0.0
 * @author Gustavo Huilca Chipana
 */
public class EmailAleradyExistsException extends RuntimeException{
    public EmailAleradyExistsException(String message) {
        super(message);
    }
}

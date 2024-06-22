package upc.edu.artcollab.api.users.domain.model.exceptions;

/**
 * Exception thrown when a username already exists
 * <p>
 *     This exception is thrown when a username already exists in the system.
 * </p>
 * @version 1.0.0
 * @author Gustavo Huilca Chipana
 */
public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}

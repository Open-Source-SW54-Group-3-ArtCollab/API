package upc.edu.artcollab.api.users.domain.model.exceptions;

/**
 *   InvalidUserTypeException
 *   <p>
 *       This class represents the exception that is thrown when an invalid user type is provided.
 *       This exception is thrown when the user type is not one of the valid user types.
 *   </p>
 * @author Gustavo Huilca Chipana
 * @version 1.0
 */
public class InvalidUserTypeException extends RuntimeException{
    public InvalidUserTypeException(String message) {
        super(message);
    }
}

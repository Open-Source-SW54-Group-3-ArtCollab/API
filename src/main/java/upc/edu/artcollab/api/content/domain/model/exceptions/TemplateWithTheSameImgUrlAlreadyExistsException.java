package upc.edu.artcollab.api.content.domain.model.exceptions;

/**
 * TemplateWithTheSameDescriptionAlreadyExistsException class is a custom exception that represents the scenario when a template with the same description already exists.
 * <p>
 *     This class represents the exception that is thrown when a template with the same description already exists in the database.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
public class TemplateWithTheSameImgUrlAlreadyExistsException extends RuntimeException {
    public TemplateWithTheSameImgUrlAlreadyExistsException(String message) {
        super(message);
    }
}

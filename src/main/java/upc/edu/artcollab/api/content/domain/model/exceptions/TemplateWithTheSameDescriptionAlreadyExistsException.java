package upc.edu.artcollab.api.content.domain.model.exceptions;

public class TemplateWithTheSameDescriptionAlreadyExistsException extends RuntimeException{
    public TemplateWithTheSameDescriptionAlreadyExistsException(String message) {
        super(message);
    }
}

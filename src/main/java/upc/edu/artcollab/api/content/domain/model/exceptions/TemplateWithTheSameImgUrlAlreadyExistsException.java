package upc.edu.artcollab.api.content.domain.model.exceptions;

public class TemplateWithTheSameImgUrlAlreadyExistsException extends RuntimeException {
    public TemplateWithTheSameImgUrlAlreadyExistsException(String message) {
        super(message);
    }
}

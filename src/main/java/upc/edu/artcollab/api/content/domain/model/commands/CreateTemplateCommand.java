package upc.edu.artcollab.api.content.domain.model.commands;

public record CreateTemplateCommand(String title, String description, String type, String imgURL, Integer views, Integer likes, String genre, Integer templateState_Id, Integer templateHistory_Id, Integer portfolio_Id) {
    public CreateTemplateCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }
        if (imgURL == null || imgURL.isBlank()) {
            throw new IllegalArgumentException("imgURL cannot be null or empty");
        }
        if (views == null) {
            throw new IllegalArgumentException("views cannot be null");
        }
        if (likes == null) {
            throw new IllegalArgumentException("likes cannot be null");
        }
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("genre cannot be null or empty");
        }
        if (templateState_Id == null) {
            throw new IllegalArgumentException("templateState_Id cannot be null");
        }
        if (templateHistory_Id == null) {
            throw new IllegalArgumentException("templateHistory_Id cannot be null");
        }
        if (portfolio_Id == null) {
            throw new IllegalArgumentException("portfolio_Id cannot be null");
        }
    }
}

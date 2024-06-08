package upc.edu.artcollab.api.content.interfaces.rest.resources;

public record CreateTemplateResource (String title, String description, String type, String imgURL, Integer views, Integer likes, String genre, Integer templateState_Id, Integer templateHistory_Id, Integer portfolio_Id) {
    public CreateTemplateResource {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (imgURL == null) {
            throw new IllegalArgumentException("imgURL cannot be null");
        }
        if (views == null) {
            throw new IllegalArgumentException("views cannot be null");
        }
        if (likes == null) {
            throw new IllegalArgumentException("likes cannot be null");
        }
        if (genre == null) {
            throw new IllegalArgumentException("genre cannot be null");
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

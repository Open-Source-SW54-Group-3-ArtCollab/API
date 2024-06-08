package upc.edu.artcollab.api.content.domain.model.queries;

public record GetAllTemplatesByGenreQuery(String genre) {
    public GetAllTemplatesByGenreQuery {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("The genre cannot be null or empty");
        }
    }
}

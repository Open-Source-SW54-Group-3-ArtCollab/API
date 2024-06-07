package upc.edu.artcollab.api.content.domain.model.queries;

public record GetTemplateByGenreQuery(String genre) {
    public GetTemplateByGenreQuery {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("The genre cannot be null or empty");
        }
    }
}

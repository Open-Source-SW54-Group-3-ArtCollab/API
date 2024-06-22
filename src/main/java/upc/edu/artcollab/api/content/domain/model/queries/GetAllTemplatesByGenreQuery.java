package upc.edu.artcollab.api.content.domain.model.queries;

/**
 * The GetAllTemplatesByGenreQuery class is a query model.
 * <p>
 *     This class represents the query model for the GetAllTemplatesByGenreQuery.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
public record GetAllTemplatesByGenreQuery(String genre) {
    public GetAllTemplatesByGenreQuery {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("The genre cannot be null or empty");
        }
    }
}

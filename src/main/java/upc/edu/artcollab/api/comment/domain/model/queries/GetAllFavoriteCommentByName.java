package upc.edu.artcollab.api.comment.domain.model.queries;

public record GetAllFavoriteCommentByName(String name) {
    public GetAllFavoriteCommentByName {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
    }
}

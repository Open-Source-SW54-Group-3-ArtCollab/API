package upc.edu.artcollab.api.comment.domain.model.queries;

public record GetFavoriteCommentByIdQuery(Long commentId) {
    public GetFavoriteCommentByIdQuery {
        if (commentId == null || commentId <= 0) {
            throw new IllegalArgumentException("CommentId cannot be null or empty");
        }
    }
}

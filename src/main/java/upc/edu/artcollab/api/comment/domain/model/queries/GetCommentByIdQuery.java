package upc.edu.artcollab.api.comment.domain.model.queries;

public record GetCommentByIdQuery(Long commentId) {
    public GetCommentByIdQuery {
        if (commentId == null || commentId <= 0) {
            throw new IllegalArgumentException("CommentId cannot be null or empty");
        }
    }
}

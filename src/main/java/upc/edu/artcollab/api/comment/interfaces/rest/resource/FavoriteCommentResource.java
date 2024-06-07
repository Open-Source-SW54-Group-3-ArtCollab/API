package upc.edu.artcollab.api.comment.interfaces.rest.resource;

public record FavoriteCommentResource(Long commentid, String name, String image, String content, String ranklvl, Integer likes, Integer dislikes) {
}

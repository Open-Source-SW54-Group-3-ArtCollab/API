package upc.edu.artcollab.api.comment.domain.model.commands;

public record CreateFavoriteCommentCommand(String name, String image, String content, String ranklvl, Integer likes, Integer dislikes) {
    public CreateFavoriteCommentCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        if (ranklvl == null || ranklvl.isBlank()) {
            throw new IllegalArgumentException("Rank cannot be null or empty");
        }
        if (likes == null || likes <= 0) {
            throw new IllegalArgumentException("Likes cannot be null or empty");
        }
        if (dislikes == null || dislikes <= 0) {
            throw new IllegalArgumentException("Dislikes cannot be null or empty");
        }
    }
}

package upc.edu.artcollab.api.users.domain.model.commands;

public record CreateReaderCommand(String name, String username, String email, String password, String type, String imgUrl) {
    public CreateReaderCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name must not be null or empty.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("The username must not be null or empty.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("The email must not be null or empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("The password must not be null or empty.");
        }

        if (type != null && !type.toLowerCase().equals("reader") && !type.toLowerCase().equals("artist")) {
            throw new IllegalArgumentException("The type must be either reader or artist.");
        }
        if (password.length() < 6 || password.length() > 16) {
            throw new IllegalArgumentException("The password must be between 6 and 16 characters.");
        }
        if (username.length() > 20) {
            throw new IllegalArgumentException("The username must be less than 20 characters.");
        }
    }
}

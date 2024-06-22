package upc.edu.artcollab.api.users.domain.model.queries;

public record GetReaderByEmailAndPasswordQuery (String email, String password){
    public GetReaderByEmailAndPasswordQuery {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("The email must not be null or empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("The password must not be null or empty.");
        }
    }
}

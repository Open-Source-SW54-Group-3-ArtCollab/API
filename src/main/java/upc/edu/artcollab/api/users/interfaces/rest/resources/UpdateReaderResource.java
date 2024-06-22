package upc.edu.artcollab.api.users.interfaces.rest.resources;

import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

public record UpdateReaderResource(String name, String username, String email, String password, UserTypes type, String imgUrl) {
}

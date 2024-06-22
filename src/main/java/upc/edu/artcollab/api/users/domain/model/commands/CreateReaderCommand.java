package upc.edu.artcollab.api.users.domain.model.commands;


import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

public record CreateReaderCommand(String name, String username, String email, String password, UserTypes type, String imgUrl) {
}

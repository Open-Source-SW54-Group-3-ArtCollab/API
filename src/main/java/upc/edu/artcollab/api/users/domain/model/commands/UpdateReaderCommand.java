package upc.edu.artcollab.api.users.domain.model.commands;

import upc.edu.artcollab.api.users.domain.model.valueobjects.UserTypes;

public record UpdateReaderCommand(long id,String name, String username, String email, String password, UserTypes type, String imgUrl) {
}

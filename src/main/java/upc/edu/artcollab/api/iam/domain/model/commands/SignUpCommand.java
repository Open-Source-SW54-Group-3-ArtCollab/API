package upc.edu.artcollab.api.iam.domain.model.commands;

import upc.edu.artcollab.api.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password,List<Role>roles) {
}

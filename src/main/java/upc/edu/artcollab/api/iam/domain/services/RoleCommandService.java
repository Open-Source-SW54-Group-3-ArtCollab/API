package upc.edu.artcollab.api.iam.domain.services;

import upc.edu.artcollab.api.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}

package upc.edu.artcollab.api.iam.domain.services;

import upc.edu.artcollab.api.iam.domain.model.entities.Role;
import upc.edu.artcollab.api.iam.domain.model.queries.GetAllRolesQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}

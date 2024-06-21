package upc.edu.artcollab.api.iam.interfaces.rest.transform;

import upc.edu.artcollab.api.iam.domain.model.entities.Role;
import upc.edu.artcollab.api.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
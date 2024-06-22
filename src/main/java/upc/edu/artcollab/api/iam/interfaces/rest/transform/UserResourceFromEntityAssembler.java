package upc.edu.artcollab.api.iam.interfaces.rest.transform;

import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.domain.model.entities.Role;
import upc.edu.artcollab.api.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
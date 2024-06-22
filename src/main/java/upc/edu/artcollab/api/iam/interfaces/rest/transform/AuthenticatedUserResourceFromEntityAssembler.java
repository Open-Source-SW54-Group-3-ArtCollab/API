package upc.edu.artcollab.api.iam.interfaces.rest.transform;

import upc.edu.artcollab.api.iam.domain.model.aggregates.User;
import upc.edu.artcollab.api.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
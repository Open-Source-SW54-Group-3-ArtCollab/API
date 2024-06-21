package upc.edu.artcollab.api.iam.domain.model.queries;

import upc.edu.artcollab.api.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}

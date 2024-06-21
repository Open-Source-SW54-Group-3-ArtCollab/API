package upc.edu.artcollab.api.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.iam.domain.model.entities.Role;
import upc.edu.artcollab.api.iam.domain.model.queries.GetAllRolesQuery;
import upc.edu.artcollab.api.iam.domain.model.queries.GetRoleByNameQuery;
import upc.edu.artcollab.api.iam.domain.services.RoleQueryService;
import upc.edu.artcollab.api.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
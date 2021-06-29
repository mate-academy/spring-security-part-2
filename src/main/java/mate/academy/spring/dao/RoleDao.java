package mate.academy.spring.dao;

import mate.academy.spring.model.Role;
import java.util.Optional;

public interface RoleDao {
    Optional<Role> getByRoleName(Role.RoleName name);
}

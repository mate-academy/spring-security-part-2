package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(Role.RoleName roleName);
}

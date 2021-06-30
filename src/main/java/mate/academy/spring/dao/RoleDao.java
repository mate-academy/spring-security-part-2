package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;

public interface RoleDao {
    Optional<Role> getRoleByName(String roleName);

    Role add(Role role);
}

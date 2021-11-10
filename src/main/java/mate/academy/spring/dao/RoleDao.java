package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleType;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getRoleByName(RoleType roleName);
}

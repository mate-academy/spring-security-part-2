package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;

public interface RoleDao {
    mate.academy.spring.model.Role add(Role role);

    Optional<Role> getByName(String roleName);
}

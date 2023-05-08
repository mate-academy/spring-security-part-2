package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.Role;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> get(Long id);

    Optional<Role> getByName(Role.RoleName name);

    List<Role> getAll();
}

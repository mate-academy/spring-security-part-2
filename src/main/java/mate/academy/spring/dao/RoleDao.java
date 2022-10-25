package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.dao.type.RoleNames;
import mate.academy.spring.model.Role;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getByName(RoleNames roleName);
}

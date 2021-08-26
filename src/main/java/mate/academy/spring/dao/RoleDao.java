package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.util.RoleType;

public interface RoleDao {

    Optional<Role> getRoleByName(RoleType roleName);
}

package mate.academy.spring.dao;

import java.util.Optional;
import mate.academy.spring.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Optional<Role> getRoleByName(String roleName);
}

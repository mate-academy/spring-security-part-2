package mate.academy.spring.dao;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.RolesEnum;

public interface RoleDao {
    Role add(Role role);

    Role getRoleByName(RolesEnum roleName);
}

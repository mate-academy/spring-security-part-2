package mate.academy.spring.dao;

import mate.academy.spring.model.Role;

public interface RoleServiseDao {
    Role add(Role role);

    Role getRoleByName(String roleName);
}

package mate.academy.spring.dao;

import mate.academy.spring.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}

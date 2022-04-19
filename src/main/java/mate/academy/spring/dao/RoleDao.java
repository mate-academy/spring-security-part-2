package mate.academy.spring.dao;

import mate.academy.spring.model.Role;

public interface RoleDao {

    Role getByName(String roleName);
}

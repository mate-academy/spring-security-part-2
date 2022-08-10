package mate.academy.spring.service;

import mate.academy.spring.model.Role;

public interface RoleService {
    Role save(Role role);

    Role getRoleByName(String name);
}

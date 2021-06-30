package mate.academy.spring.service;

import mate.academy.spring.model.Role;

public interface RoleService {
    Role getRoleByName(String roleName);

    Role add(Role role);
}

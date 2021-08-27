package mate.academy.spring.service;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(RoleName roleName);
}

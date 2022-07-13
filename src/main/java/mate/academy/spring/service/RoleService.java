package mate.academy.spring.service;

import mate.academy.spring.model.Role;

public interface RoleService {
    Role getByName(String roleName);

    Role add(Role roleName);
}

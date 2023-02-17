package mate.academy.spring.service;

import mate.academy.spring.model.Role;

import java.util.Optional;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);
}

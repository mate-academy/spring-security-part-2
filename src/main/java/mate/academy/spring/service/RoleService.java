package mate.academy.spring.service;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.enums.RoleEnum;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(RoleEnum name);
}

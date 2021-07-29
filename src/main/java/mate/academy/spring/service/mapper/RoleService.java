package mate.academy.spring.service.mapper;

import mate.academy.spring.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}

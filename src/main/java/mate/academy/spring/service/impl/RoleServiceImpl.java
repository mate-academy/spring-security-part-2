package mate.academy.spring.service.impl;

import java.util.Arrays;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;

public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        Role.RoleName roleEnum = Arrays.stream(Role.RoleName.values())
                .filter(r -> r.name().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Role with name " + roleName + " doesn't exist"));
        return roleDao.getByName(roleEnum).orElseThrow(() ->
                new RuntimeException("Can't get role by name " + roleName));
    }
}

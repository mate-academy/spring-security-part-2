package mate.academy.spring.service.impl;

import java.util.Arrays;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

@Service
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
        Role.RoleName role = Arrays.stream(Role.RoleName.values())
                .filter(r -> r.name().equals(roleName.toUpperCase()))
                .findFirst()
                .get();
        return roleDao.getByName(role).get();
    }
}

package mate.academy.spring.service.impl;

import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
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
    public Role getRoleByName(RoleName name) {
        return roleDao.getRoleByName(name)
                .orElseThrow(() ->
                        new RuntimeException("Role with name " + name + "not found"));
    }
}

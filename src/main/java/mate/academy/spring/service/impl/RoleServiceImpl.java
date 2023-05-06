package mate.academy.spring.service.impl;

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
    public Role get(Long id) {
        return roleDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get role by id " + id));
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(Role.RoleName.valueOf(name)).orElseThrow(
                () -> new RuntimeException("Can't get role by name " + name));
    }
}

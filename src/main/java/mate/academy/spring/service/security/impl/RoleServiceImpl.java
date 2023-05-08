package mate.academy.spring.service.security.impl;

import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.security.RoleService;
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
    public Role getByName(Role.RoleName roleName) {
        return roleDao.getByName(roleName).orElseThrow(() ->
                new DataProcessingException("Role with name " + roleName.name() + " not found"));
    }
}

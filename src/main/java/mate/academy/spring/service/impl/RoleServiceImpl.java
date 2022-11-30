package mate.academy.spring.service.impl;

import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
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
        if (!roleName.equals(Role.RoleName.ADMIN.name())
                && !roleName.equals(Role.RoleName.USER.name())) {
            throw new DataProcessingException("Role " + roleName
                    + "didn't find", new RuntimeException());
        }
        return roleName.equals(Role.RoleName.USER.name())
                ? roleDao.getByName(Role.RoleName.USER).get() :
                roleDao.getByName(Role.RoleName.ADMIN).get();
    }
}

package mate.academy.spring.service.impl;

import java.util.NoSuchElementException;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleType;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(RoleType roleName) {
        return roleDao.getRoleByName(roleName)
                .orElseThrow(() -> new NoSuchElementException("Role " + roleName.name()
                        + " wasn't found"));
    }
}

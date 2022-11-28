package mate.academy.spring.service.impl;

import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        Optional<Role> roleOptional = roleDao.getByName(role.getRoleName().name());
        if (roleOptional.isEmpty()) {
            return roleDao.add(role);
        }
        role.setId(roleOptional.get().getId());
        return role;
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName).orElseThrow(
                () -> new RuntimeException("Role with rolename " + roleName + " not found."));
    }
}

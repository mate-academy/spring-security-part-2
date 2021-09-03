package mate.academy.spring.service.impl;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.util.RoleType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(RoleType roleName) {
        return roleDao.getRoleByName(roleName).orElseThrow(()
                -> new NoSuchElementException("Role " + roleName + " wasn't found"));
    }
}

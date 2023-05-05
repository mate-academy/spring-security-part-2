package mate.academy.spring.service.impl;

import java.util.NoSuchElementException;
import mate.academy.spring.dao.RoldeDao;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoldeDao roldeDao;

    public RoleServiceImpl(RoldeDao roldeDao) {
        this.roldeDao = roldeDao;
    }

    @Override
    public Role add(Role role) {
        return roldeDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roldeDao.getByName(roleName).orElseThrow(() ->
                new NoSuchElementException("Can`t get role by role: " + roleName));
    }
}

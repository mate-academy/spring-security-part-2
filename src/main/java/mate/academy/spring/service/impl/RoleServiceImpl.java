package mate.academy.spring.service.impl;

import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao dao;

    public RoleServiceImpl(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    public Role add(Role role) {
        return dao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        return dao.getByName(roleName).orElseThrow(
                () -> new DataProcessingException("Can't found role by name: " + roleName));
    }
}

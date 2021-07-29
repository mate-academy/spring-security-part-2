package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.model.Role;
import org.hibernate.SessionFactory;

public class RoleDaoImpl extends AbstractDao implements RoleDao {
    public RoleDaoImpl(SessionFactory factory, Class clazz) {
        super(factory, clazz);
    }

    @Override
    public void add(Role role) {

    }

    @Override
    public Role getRoleByName(String roleName) {
        return null;
    }
}

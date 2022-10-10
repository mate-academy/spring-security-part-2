package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import mate.academy.spring.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Role getByName(String roleName) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Role r WHERE r.roleName =:rolename",
                            Role.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Not found role for by name: " + roleName, e);
        }
    }
}

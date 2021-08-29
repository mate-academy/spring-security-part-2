package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Role r where r.RoleType = :role", Role.class)
                    .setParameter("role", Role.RoleType.valueOf(roleName))
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Role by RoleType name: " + roleName, e);
        }
    }
}

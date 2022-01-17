package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory, Class<Role> clazz) {
        super(factory, clazz);
    }

    @Override
    public Role getByName(String name) {
        try (Session session = factory.openSession()) {
            Query<Role> findByEmail = session.createQuery(
                    "FROM Role WHERE name = :name", Role.class);
            findByEmail.setParameter("name", name);
            return findByEmail.uniqueResultOptional().get();
        } catch (Exception e) {
            throw new DataProcessingException("Role with name " + name + " not found", e);
        }
    }
}

package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getByRoleName = session.createQuery(
                    "FROM Role WHERE roleName = :role", Role.class);
            getByRoleName.setParameter("role", roleName);
            return getByRoleName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Not role found with name: " + roleName, e);
        }
    }
}

package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.dao.type.RoleNames;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(RoleNames roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> queryGetByName = session.createQuery("from Role where roleName = :name",
                    Role.class);
            queryGetByName.setParameter("name", roleName);
            return queryGetByName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get role from DB by name: " + roleName, e);
        }
    }
}

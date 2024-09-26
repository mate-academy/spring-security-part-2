package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.Role.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Role getByName(RoleName roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> query = session.createQuery(
                    "FROM Role WHERE name = :roleName", Role.class
            );
            query.setParameter("roleName", roleName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Role " + roleName + " isn't found", e);
        }
    }
}

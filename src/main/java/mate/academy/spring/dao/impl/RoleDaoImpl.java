package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory, Class<Role> clazz) {
        super(factory, clazz);
    }

    @Override
    public Role getByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getByName =
                    session.createQuery("from Role r where r.roleName = :roleName", Role.class);
            getByName.setParameter("roleName", roleName);
            return getByName.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Not found role for name: " + roleName.toString(), e);
        }
    }
}

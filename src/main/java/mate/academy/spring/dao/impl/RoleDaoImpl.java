package mate.academy.spring.dao.impl;

import java.util.Optional;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleType;
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
    public Role add(Role role) {
        return super.add(role);
    }

    @Override
    public Optional<Role> getRoleByName(RoleType roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getRoleByNameQuery = session.createQuery("FROM Role "
                    + "WHERE roleName =: roleName", Role.class);
            getRoleByNameQuery.setParameter("roleName", roleName);
            return getRoleByNameQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't found role with name " + roleName, e);
        }
    }
}

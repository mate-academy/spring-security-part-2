package mate.academy.spring.dao.impl;

import java.util.Optional;
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

    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Role add(Role role) {
        Optional<Role> roleOptional = getByName(role.getRoleName().name());
        if (roleOptional.isEmpty()) {
            return super.add(role);
        }
        role.setId(roleOptional.get().getId());
        return role;
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getByName = session.createQuery("FROM Role"
                    + " WHERE roleName = :enumRoleName", Role.class);
            getByName.setParameter("enumRoleName", Role.RoleName.valueOf(roleName));
            return getByName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Role with roleName: " + roleName
                    + " not found. " + e.getMessage(), e);
        }
    }
}

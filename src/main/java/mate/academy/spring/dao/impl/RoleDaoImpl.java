package mate.academy.spring.dao.impl;

import java.util.Optional;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t create role to DB: " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> roleQuery
                    = session.createQuery("FROM Role WHERE role_name = :role", Role.class);
            roleQuery.setParameter("role", roleName);
            return roleQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find role: " + roleName, e);
        }
    }
}

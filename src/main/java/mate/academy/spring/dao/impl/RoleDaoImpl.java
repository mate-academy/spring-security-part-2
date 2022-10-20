package mate.academy.spring.dao.impl;

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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create role" + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return role;
    }

    @Override
    public Role getByName(String role) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> roleQuery =
                    session.createQuery("FROM Role r WHERE r.role = :role", Role.class);
            roleQuery.setParameter("role", role);
            return roleQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find role from db by:" + role, e);
        }
    }
}

package mate.academy.spring.dao.impl;

import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.CinemaHallDao;
import mate.academy.spring.model.CinemaHall;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    @Autowired
    public CinemaHallDaoImpl(SessionFactory factory) {
        super(factory, CinemaHall.class);
    }
}

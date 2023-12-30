package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.StatDAO;
import ru.javabegin.hibernate.entity.Stat;
import ru.javabegin.hibernate.entity.User;

public class StatDAOImpl implements StatDAO {
    @Override
    public Stat getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Stat> query = session.createQuery("FROM Stat WHERE user.email LIKE :email", Stat.class);
        query.setParameter("email", "%" + email + "%");
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }

    @Override
    public Stat getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Stat> query = session.createQuery("FROM Stat WHERE user.email LIKE :email", Stat.class);
        query.setParameter("email", "%" + user.getEmail() + "%");
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }
}

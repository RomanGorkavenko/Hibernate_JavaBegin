package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.PriorityDAO;
import ru.javabegin.hibernate.entity.Priority;

import java.util.List;

// реализация операций Priority

public class PriorityDAOImpl implements PriorityDAO {

    @Override
    public List<Priority> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Priority> query = session.createQuery("FROM Priority", Priority.class);
        List<Priority> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public List<Priority> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Priority> query = session.createQuery("FROM Priority p where p.user.email like :email", Priority.class);
        query.setParameter("email", "%"+email+"%");
        List<Priority> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public Priority get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Priority user = session.get(Priority.class, id); // получаем единичный объект
        session.close();
        return user;
    }

    @Override
    public void update(Priority obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        session.merge(obj); // если id объекта НЕ будет заполнено - выйдет ошибка (Hibernate не поймет какую именно строку хотите обновить)
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        Priority u = new Priority();// создаем временный объект и заполняем его id
        u.setId(id);
        session.remove(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Priority obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        session.persist(obj); // если id объекта будет заполнено - БД перезапишет это поле
        session.getTransaction().commit();
        session.close();
    }


}

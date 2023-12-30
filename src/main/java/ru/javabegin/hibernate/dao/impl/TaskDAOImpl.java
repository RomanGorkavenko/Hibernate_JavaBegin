package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.TaskDAO;
import ru.javabegin.hibernate.entity.Task;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    @Override
    public List<Task> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task", Task.class);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public List<Task> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t WHERE t.user.email LIKE :email", Task.class);
        query.setParameter("email", "%" + email + "%");
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public Task get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }

    @Override
    public void update(Task obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Task task = new Task();
        task.setId(id);
        session.remove(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Task obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> find(boolean completed, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t WHERE t.user.email LIKE :email AND t.completed = :completed", Task.class);
        query.setParameter("email", "%" + email + "%");
        query.setParameter("completed", completed);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

}

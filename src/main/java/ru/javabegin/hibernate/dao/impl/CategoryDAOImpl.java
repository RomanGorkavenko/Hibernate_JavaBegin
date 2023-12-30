package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.CategoryDAO;
import ru.javabegin.hibernate.entity.Category;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findAllByUserId(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category c WHERE c.user.id=:userId", Category.class);
        query.setParameter("userId", userId);
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }

    @Override
    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category", Category.class);
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }

    @Override
    public List<Category> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category c WHERE c.user.email LIKE :email", Category.class);
        query.setParameter("email", "%" + email + "%");
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }

    @Override
    public Category get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    public void update(Category obj) {
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
        Category category = new Category();
        category.setId(id);
        session.remove(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Category obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        session.close();
    }
}

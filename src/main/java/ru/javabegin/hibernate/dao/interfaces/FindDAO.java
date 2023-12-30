package ru.javabegin.hibernate.dao.interfaces;

import ru.javabegin.hibernate.entity.User;

import java.util.List;

public interface FindDAO<T> {

    /**
     * Получить абсолютно все значения
     */
    List<T> findAll();

    /**
     * Получить все значения по значению почты
     * @param email почта User {@link User#getEmail()}
     * @return список объектов
     */
    List<T> findAll(String email);

}

package ru.javabegin.hibernate.dao.interfaces;

/**
 * Основные операции для всех entity классов
 * CRUD - Create, Read, Update, Delete
 * Общий поиск данных
 * @param <T> обобщение для реализации метода подстановки
 */
public interface CommonDAO<T> {

    /**
     * Получить одно значение по id
     * @param id уникальный идентификатор
     * @return объект
     */
    T get(Long id);

    /**
     * Обновить значение
     * @param obj объект
     */
    void update(T obj);

    /**
     * Удалить значение по id
     * @param id уникальный идентификатор
     */
    void delete(Long id);

    /**
     * Добавить значение
     * @param obj объект
     */
    void add(T obj);

}

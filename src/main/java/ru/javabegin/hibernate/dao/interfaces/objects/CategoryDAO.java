package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.dao.interfaces.CommonDAO;
import ru.javabegin.hibernate.dao.interfaces.FindDAO;
import ru.javabegin.hibernate.entity.Category;

import java.util.List;

public interface CategoryDAO extends CommonDAO<Category>, FindDAO<Category> {

    List<Category> findAllByUserId(Long userId);

}

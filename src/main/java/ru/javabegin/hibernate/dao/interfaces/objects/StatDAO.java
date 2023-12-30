package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.entity.Stat;
import ru.javabegin.hibernate.entity.User;

public interface StatDAO {

    Stat getByUser(String email);
    Stat getByUser(User user);

}

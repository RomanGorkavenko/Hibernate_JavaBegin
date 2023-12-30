package ru.javabegin.hibernate;


import lombok.extern.log4j.Log4j2;
import ru.javabegin.hibernate.dao.impl.*;
import ru.javabegin.hibernate.entity.Stat;
import ru.javabegin.hibernate.entity.User;

@Log4j2
public class Main {

    public static void main(String[] args) {

/*      //создание объекта User с помощью new, запись его в базу и обновление.

        log.info("Started lombok logging");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        // открыть транзакцию
        session.getTransaction().begin();

        log.info("Transaction start");

        User user = new User(); // состояние NEW (transient)
        user.setUsername("Bob10002");
        user.setPassword("passwordApp10001");
        user.setEmail("email10002@email.com");
        // Id не заполняем, т.к. он сгенерится автоматически в БД и присвоится в поле

        session.persist(user); // состояние managed (persistent) - управляемый объект - контейнером Hibernate

        session.getTransaction().commit(); // сохранить изменения (транзакция завершается)

        System.out.println("user.getId() = " + user.getId());

        session.close(); // закрыть сессию

        // состояние объекта detached

        session = HibernateUtil.getSessionFactory().openSession();

        // открыть транзакцию
        session.getTransaction().begin();

        user.setUsername("Bob10001");
        user.setEmail("email10001@email.com");

        // т.к. мы хотим обновить уже существующий объект в БД, то вместо persist используем метод merge,
        // который по id находит запись и обновляет для нее новые данные
        // При попытке вызвать persist - вы получите ошибку "detached entity passed to persist", т.к.
        // User создавался еще в прошлой сессии
        session.merge(user); //  заново присоединяем ранее созданный объект User

        session.getTransaction().commit(); // сохранить изменения (транзакция завершается)

        session.close(); // закрыть сессию

        // состояние объекта detached

        HibernateUtil.close(); // закрыть Session Factory

*/

        // Запросы с помощью Criteria (динамические запросы)
/*      // SELECT по условию WHERE
        log.info("Started lombok logging");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        // подготовка запроса - получение всех пользователей
        // создание фабрики запросов
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // создание запроса в фабрике и указание класса entity
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        // добавление в запрос from с указанием таблицы в виде класса entity
        Root<User> root = criteriaQuery.from(User.class);
        // добавление в запрос select с уже ранее созданным from
        criteriaQuery.select(root);

        // select по условию (where), gt -> greaterThan (больше чем)
        criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("id"), 10000));

        // select по условию (where and..)
        Predicate p1 = criteriaBuilder.gt(root.get("id"), 9800);
        Predicate p2 = criteriaBuilder.lt(root.get("id"), 20000);

        criteriaQuery.select(root).where(criteriaBuilder.and(p1, p2));

        // выполнение запроса с получением результата
        Query query = session.createQuery(criteriaQuery);
        List<User> users = query.getResultList();

        // если у вас в таблице много пользователей, то при выводе их в консоль,
        // может немного подтормаживать
        log.info(users);

        // можно вывести просто кол-во полученных данных, чтобы не заполнять всю консоль пользователями
        log.info(users.size());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*      // DELETE удаление по условию
        log.info("Started lombok logging");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        // создание фабрики запросов
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // создание запроса в фабрике и указание класса entity
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        // добавление в запрос from с указанием таблицы в виде класса entity
        Root<User> root = criteriaDelete.from(User.class);
        // создаем условие where (по каким параметрам удаляем)
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), 10002));

        // открываем транзакцию
        Transaction transaction = session.beginTransaction();
        // выполняем executeUpdate (запрос на изменение данных)
        session.createMutationQuery(criteriaDelete).executeUpdate();
        // закрываем транзакцию сохраняя изменения
        transaction.commit();



        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*        // UPDATE обновление по условию
        log.info("Started lombok logging");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        // создание фабрики запросов
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // создание запроса в фабрике и указание класса entity
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        // добавление в запрос from с указанием таблицы в виде класса entity
        Root<User> root = criteriaUpdate.from(User.class);

        // указываем что мы хотим обновить (какое поле и какое значение)
        criteriaUpdate.set("email", "criteriaUpdate@email.com");
        // создаем условие where (по каким параметрам обновляем)
        // если не указывать то обновятся все записи
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), 10001));

        // открываем транзакцию
        Transaction transaction = session.beginTransaction();
        // выполняем executeUpdate (запрос на изменение данных)
        session.createMutationQuery(criteriaUpdate).executeUpdate();
        // закрываем транзакцию сохраняя изменения
        transaction.commit();



        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

        // Запросы с помощью HQL-JPQL

/*      //SELECT

        log.info("SELECT HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query =  session.createQuery("FROM User", User.class);
        List<User> users = query.getResultList();

        log.info(users.size());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*   //SELECT WHERE

        log.info("SELECT WHERE HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query =  session.createQuery("FROM User u WHERE u.email LIKE :text AND u.id = :number",
                User.class);
        query.setParameter("text", "%a%");
        query.setParameter("number", 10001L);
        List<User> users = query.getResultList();

        log.info(users.size());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*      //SELECT LIMIT, OFFSET

        log.info("SELECT LIMIT, OFFSET HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query =  session.createQuery("FROM User", User.class);
        query.setFirstResult(1);
        query.setMaxResults(90);
        List<User> users = query.getResultList();

        log.info(users.size());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*      //SELECT получение одного объекта (uniqueResult)

        log.info("SELECT получение одного объекта (uniqueResult) HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        //import org.hibernate.query.Query;
        //вместо import jakarta.persistence.Query;
        Query<User> query =  session.createQuery("FROM User u WHERE u.id = :number", User.class);
        query.setParameter("number", 10001L);
        User user = query.uniqueResult();

        log.info(user.getUsername());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*

        //Функция агрегации (avg, count, max, min, sum)

        log.info("Функция агрегации (avg, count, max, min, sum) HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        //import org.hibernate.query.Query;
        //вместо import jakarta.persistence.Query;
        Query<Long> query =  session.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.email LIKE :text",
                Long.class);
        query.setParameter("text", "%email%");
        Long count = query.uniqueResult();

        log.info(count);

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/
/*

        //Выборка нескольких полей

        log.info("Выборка нескольких полей HQL-JPQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        //import org.hibernate.query.Query;
        //вместо import jakarta.persistence.Query;
        Query<User> query = session.createQuery("SELECT new User(u.email, u.username) FROM User u WHERE u.id = :id",
                User.class);
        query.setParameter("id", 30L);

        User user = query.uniqueResult();

        log.info(user.getUsername());

        Query<User> query2 = session.createQuery("SELECT new User(u.email, u.username) FROM User u WHERE u.id > 9990",
                User.class);

        List<User> users = query2.getResultList();

        for (User user1 : users) {
            log.info(user1.getUsername());
        }

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

        //Native SQL

/*        log.info("Native SQL");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        String query = "SELECT * FROM todolist.user_data";
        NativeQuery<User> sqlQuery = session.createNativeQuery(query, User.class);

        sqlQuery.setMaxResults(10);

        List<User> userList = sqlQuery.list();

        log.info(userList.size());
        
        //Object[]
        
        String query2 = """
                SELECT
                    count(u.id),
                    substring(u.email, position('@' in u.email) + 1, length(u.email)) AS domainemail
                FROM todolist.user_data u
                WHERE u.email LIKE '%@%'
                GROUP BY substring(u.email, position('@' in u.email) + 1, length(u.email))""";

        NativeQuery<Object[]> sqlQuery2 = session.createNativeQuery(query2, Object[].class);

        log.info(sqlQuery2.list());

        for (Object[] o: sqlQuery2.list()) {
            log.info(o[0]);
            log.info(o[1]);
        }

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/
/*
        // Связи и ленивая загрузка

        log.info("Получение объекта по ID");

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = session.get(User.class, 12L);

        log.info(user.getCategories());
        log.info(user.getPriorities());

        session.close(); // закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*      log.info("Hibernate tutorial started");

        //сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        User u1 = session.get(User.class, 12L);
        log.info(u1);
        session.close();// закрыть первуб сессию

        // откроем новую сессию
        session = HibernateUtil.getSessionFactory().openSession();
        User u2 = session.get(User.class, 12L); // должен получить его из L2C
        log.info(u2);

        session.close();// закрыть сессию

        log.info("hit " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
        log.info("miss " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheMissCount());
        log.info("put " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCachePutCount());

        for (String s : HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames()) {
            log.info(s);
        }

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
*/
/*
        log.info("Hibernate Удаление объекта");

        //сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
//        Получаем пользователя из базы затем удаляем
//        User u1 = session.get(User.class, 12L);
//        u1.setId(12L);

        // Создаем пользователя, через set указываем ему Id и удаляем
        User u1 = new User();
        u1.setId(12L);
        session.remove(u1); // удаление по ID пользователя

        session.getTransaction().commit();

        session.close();// закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory
*/

/*
        log.info("Hibernate Транзакция, flush, commit");

        //сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        for (int i = 1000; i < 1100; i++) {
            User user = new User();
            user.setUsername("name" + i);
            user.setEmail("name" + i);
            user.setPassword("asdasdasd");
            session.persist(user); // вместо deprecated метода save

            if (i % 100 == 0){
                session.flush(); // очищаем ОЗУ
            }

        }

        session.getTransaction().rollback();

        session.close();

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
*/

/*
        log.info("Hibernate Добавление entity Task");

        //сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();


        User t1 = session.get(User.class, 2L);


//        log.info(u1);
       log.info(t1);


        session.close();

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
*/

/*        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        log.info("Hibernate Каскадное сохранение связанных объектов");

        Role r1 = session.get(Role.class, 1);
        Role r2 = session.get(Role.class, 2);

        User u = new User();
        u.setUsername("manyroles2");
        u.setEmail("manyroles2@email.com");
        u.setPassword("asdasdasd");
        u.getRoles().add(r1); // admin
//        u.getRoles().add(r2); // user

        session.persist(u);

        session.getTransaction().commit();

        session.close();

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
*/

        log.info("Проверка (сценарии) работы DAO");

        //        СЦЕНАРИЙ (один из множества вариантов - можете придумать свой):
//        создаем пользователя (триггеры создадут сразу же тестовые данные)
//        активируем пользователя (поле activated)
//        создаем новую категорию
//        создаем новый приоритет
//        создаем несколько новых задач (помимо тестовых) с новыми категорией и приоритетом
//        завершаем задачу
//        удаляем задачу
//        считываем статистику по любой категории пользователя
//        считываем общую статистику пользователя


//        // создаем пользователя (триггеры создадут сразу же тестовые данные)
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("testuser");
//        user.setEmail("testuser@gmail.com");
//
//        UserDAOImpl userDAO = new UserDAOImpl();
//        userDAO.add(user);
//
//        // активируем пользователя (поле activated)
//        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
//        Activity activity = activityDAO.getByUser(user);
//        activity.setActivated(true);
//        activityDAO.update(activity);

        UserDAOImpl userDAO = new UserDAOImpl();

        User user = userDAO.get(10023L);


        // создаем справочные значения
        PriorityDAOImpl priorityDAO = new PriorityDAOImpl();

//        Priority priority = new Priority();
//        priority.setColor("#fff");
//        priority.setTitle("Новый приоритет");
//        priority.setUser(user);
//        priorityDAO.add(priority);

        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

//        Category category = new Category();
//        category.setTitle("Новая категория");
//        category.setUser(user);
//        categoryDAO.add(category);

        TaskDAOImpl taskDAO = new TaskDAOImpl();

//        Task task = new Task();
//        task.setUser(user);
//        task.setTitle("Супер новая задача222");
//        task.setCategory(categoryDAO.get(60055L));
//        task.setPriority(priorityDAO.get(30028L));
//        task.setTaskDate(new Date());
//        task.setCompleted(false);
//        taskDAO.add(task);

//        Task task = taskDAO.get(50046L);
//        task.setCompleted(true);
//        taskDAO.update(task);
//
//        taskDAO.delete(task);
//
        StatDAOImpl statDAO = new StatDAOImpl();
        Stat stat = statDAO.getByUser(user);

        log.info(stat.getCompletedTotal());

        log.info(categoryDAO.get(60055L).getUncompletedCount());

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня

    }

}

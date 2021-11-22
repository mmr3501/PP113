package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) not NULL, " +
                    " lastname VARCHAR (50) not NULL, " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS `mydbtest`.`users`;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка дропа таблицы");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.format("User с именем – %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            System.out.println("Ошибка добавления юзера");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления юзера");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> list = new ArrayList<>();
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            list = session.createQuery("FROM User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка получения списка юзеров");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.hibernateConnect().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка очистки таблицы");
        }
    }
}

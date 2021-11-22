package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
            dao.createUsersTable();
            dao.saveUser("Ivan", "Ivanov", (byte) 10);
            dao.saveUser("Petr", "Semenov", (byte) 100);
            dao.saveUser("Semen", "Gadzaev", (byte) 126);
            dao.saveUser("Debit", "Creditov", (byte) 49);
            List<User> list = dao.getAllUsers();
            for (User element : list) {
                System.out.println(element);
            }
            dao.cleanUsersTable();
            dao.dropUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


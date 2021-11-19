package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {
        try {
            UserDaoJDBCImpl Dao = new UserDaoJDBCImpl();
            Dao.createUsersTable();
            Dao.saveUser("Anton", "Kokin", (byte) 19);
            Dao.saveUser("Ivan", "Ivanov", (byte) 34);
            Dao.saveUser("Petr", "Petrov", (byte) 10);
            Dao.saveUser("Starik", "Starikov", (byte) 101);
            List<User> list = Dao.getAllUsers();
            for (User element : list) {
                System.out.println(element);
            }
            Dao.removeUserById(2);
            list = Dao.getAllUsers();
            for (User element : list) {
                System.out.println(element);
            }
            Dao.cleanUsersTable();
            Dao.dropUsersTable();
        } finally {
            Util.connect().close();
        }
    }
}


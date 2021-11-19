package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) not NULL, " +
                    " lastname VARCHAR (50) not NULL, " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS `mydbtest`.`users`;");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String nabor = "values('" + name +"', " + "'" + lastName + "', " + age + ");";
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("insert into users (name, lastname, age) " + nabor);
            System.out.format("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("Ошибка создания юзера");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        System.out.format("User с id – %s удален из базы данных\n", id);
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("DELETE FROM users WHERE ID = " + id);
        } catch (SQLException e) {
            System.out.format("User с id – %s не удален из базы данных\n", id);
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> list = new ArrayList<>();
        try (Statement statement = Util.connect().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("TRUNCATE `mydbtest`.`users`;");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Таблица не очищена");
            e.printStackTrace();
        }
    }

}


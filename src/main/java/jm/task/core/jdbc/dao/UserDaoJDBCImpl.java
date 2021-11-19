package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Util.connect();
            Util.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users " +
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
        try {
            Util.getStatement().executeUpdate("DROP TABLE IF EXISTS `mydbtest`.`users`;");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String nabor = "values('" + name +"', " + "'" + lastName + "', " + age + ");";
        Util.getStatement().execute("insert into users (name, lastname, age) " + nabor);
        System.out.format("User с именем – %s добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        String nabor = "DELETE FROM users WHERE ID = " + id;
        System.out.format("User с id – %s удален из базы данных\n", id);
        try {
            Util.getStatement().execute(nabor);
        } catch (SQLException e) {
            System.out.format("User с id – %s не удален из базы данных\n", id);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List <User> list = new ArrayList<>();
        ResultSet resultSet = Util.getStatement().executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setAge(resultSet.getByte(4));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Util.getStatement().execute("TRUNCATE `mydbtest`.`users`;");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Таблица не очищена");
        }
    }
}

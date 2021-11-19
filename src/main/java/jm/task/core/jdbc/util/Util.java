package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root259";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static Statement statement;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = getConnect().createStatement();
        if(!getConnect().isClosed()) {
        System.out.println("Соединение с БД установлено");
        } else {
            System.out.println("Соединение с БД не установлено");
        }
    }
    public static void closer() throws SQLException {
        try {
            getStatement().close();
            getConnect().close();
            System.out.println("Соединение закрыто");
        } catch (SQLException e) {
            System.out.println("Проблемы с закрытием соединения");
        }
    }

    public static Connection getConnect() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}

package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root259";
    private static final String PASSWORD = "root";

    public static SessionFactory hibernateConnect() {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, URL);
        dbSettings.put(Environment.USER, USERNAME);
        dbSettings.put(Environment.PASS, PASSWORD);
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        registryBuilder.applySettings(dbSettings);
        StandardServiceRegistry standardServiceRegistry = registryBuilder.build();
        MetadataSources sources = new MetadataSources(standardServiceRegistry).addAnnotatedClass(User.class);
        Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}

package query;

import entity.Client;
import entity.Planet;
import entity.Ticket;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public enum Database {
    INSTANCE;

    public SessionFactory getConnection() {
        return new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public void startMigration() {
        Properties properties = new Reader()
                .readPropertiesFile("src/main/resources/hibernate.properties");

        Flyway flyway = Flyway.configure()
                .locations("classpath:migrations")
                .dataSource(properties.getProperty("hibernate.connection.url"),
                        properties.getProperty("hibernate.connection.username"),
                        properties.getProperty("hibernate.connection.password"))
                .load();
        flyway.migrate();
    }
}

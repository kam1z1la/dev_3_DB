package query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;

import java.sql.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Database {
    INSTANCE,
    URL("jdbc:h2:D:/H2/dv_3_DV"),
    USER("admin"),
    PASSWORD("12345");

    private String data;

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
            Statement statement = connection.createStatement();
            System.out.println("The database is running: " + !statement.isClosed());
            statement.close();
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void startMigration() {
        Flyway flyway = Flyway.configure()
                .locations("classpath:migrations")
                .dataSource(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData())
                .load();
        flyway.migrate();
    }

    public static void closedConnection(Connection connection){
        try (connection) {
            System.out.println("Connection is closed!");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

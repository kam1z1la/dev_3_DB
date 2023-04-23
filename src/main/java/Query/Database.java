package Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Database {
    INSTANCE,
    URL("jdbc:h2:D:/H2/dv_3_DV"),
    USER("admin"),
    PASSWORD("12345");

    private String data;

    public void getConnection() {
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement()) {
            System.out.println("The database is running: " + !statement.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initQuery(String sqlQuery) {
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateQuery(String sqlQuery) {
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

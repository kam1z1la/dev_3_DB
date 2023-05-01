package Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

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

    public Connection getConnection()  {
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

    public void initQuery(String sqlQuery) {
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void populateQuery(String sqlQuery, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery);
             connection) {
            connection.setAutoCommit(false);
            statement.addBatch();
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

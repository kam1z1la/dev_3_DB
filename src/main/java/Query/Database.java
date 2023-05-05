package Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}

package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService extends File {

    private static void initQuery(String sqlQuery) {
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String sql = new DatabaseInitService().readFile("src/main/resources/DB/init_db.sql");
        initQuery(sql); //"drop table project_worker,project,worker,client"
    }
}

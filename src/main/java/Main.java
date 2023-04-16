
import java.sql.*;
//import org.h2.Driver;

public class Main {
    private static String URL = "jdbc:h2:D:/H2/dv_3_DV";
    private static String USERNAME = "admin";
    private static String PASSWORD = "12345";

    public static void main(String[] args) {

//        Driver driver = new Driver();
        try ( Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
//            DriverManager.registerDriver(driver);
            connection.isReadOnly();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

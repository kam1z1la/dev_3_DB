package Query;

public class DatabasePopulateService extends File {
    public static void main(String[] args) {
        String sql = new DatabasePopulateService().readFile("src/main/resources/DB/populate_db.sql");
        Database.INSTANCE.populateQuery(sql, Database.INSTANCE.getConnection());
    }
}

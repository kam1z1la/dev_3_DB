package Query;

public class DatabaseInitService extends File {
    public static void main(String[] args) {
        String sql = new DatabaseInitService().readFile("src/main/resources/DB/init_db.sql");
        Database.INSTANCE.initQuery(sql);  //"drop table project_worker,project,worker,client"
    }
}

import query.Database;

public class Main {
    public static void main(String[] args) {
        Database.INSTANCE.startMigration();
        Database.INSTANCE.getConnection();
    }
}

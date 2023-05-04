package Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;

import static Query.File.createArray;
import static Query.File.getQueryFromFile;

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
        try (PreparedStatement workerPrepared = connection
                .prepareStatement("insert into worker(name,birthday, level, salary) values (?,?,?,?)");
             PreparedStatement clientPrepared = connection
                     .prepareStatement("insert into client (name) values (?)");
             PreparedStatement projectPrepared = connection
                     .prepareStatement("insert into project(client_id, starte_date, finish_date) values (select id from client where name=?,?,?)");
             PreparedStatement project_workerPrepared = connection
                     .prepareStatement("insert into project_worker(project_id, worker_id) values (select id from project where starte_date=?,select id from worker where name=? and level=?)")) {
            connection.setAutoCommit(false);
            Object[] workerArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into worker(name,birthday, level, salary)"));
            int count = 1;
            for (var tempArray : workerArray) {
                switch (count) {
                    case 1 -> {
                        workerPrepared.setString(1, tempArray.toString().trim());
                        count = 2;
                    }
                    case 2 -> {
                        workerPrepared.setString(2, tempArray.toString().trim());
                        count = 3;
                    }
                    case 3 -> {
                        workerPrepared.setString(3, tempArray.toString().trim());
                        count = 4;
                    }
                    case 4 -> {

                        int salary = Integer.parseInt(tempArray.toString().trim());
                        workerPrepared.setInt(4, salary);
                        workerPrepared.addBatch();
                        count = 1;
                    }
                }
            }
            workerPrepared.executeBatch();

            Object[] clientArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into client(name)"));
            for (var tempArray : clientArray) {
                clientPrepared.setString(1, tempArray.toString().trim());
                clientPrepared.addBatch();
            }
            clientPrepared.executeBatch();

            Object[] projectArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into project(client_id, starte_date, finish_date)"));
            for (var tempArray : projectArray) {
                switch (count) {
                    case 1 -> {
                        String giveName = tempArray.toString().replace("select id from client where name=","").trim();
                        projectPrepared.setString(1, giveName);
                        count = 2;
                    }
                    case 2 -> {
                        projectPrepared.setString(2, tempArray.toString().trim());
                        count = 3;
                    }
                    case 3 -> {
                        projectPrepared.setString(3, tempArray.toString().trim());
                        projectPrepared.addBatch();
                        count = 1;
                    }
                }
            }
            projectPrepared.executeBatch();

            Object[] project_workerArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into project_worker(project_id, worker_id)"));
            for (var tempArray : project_workerArray) {
                switch (count) {
                    case 1 -> {
                        String giveDate = tempArray.toString().replace("select id from project where starte_date=","").trim();
                        project_workerPrepared.setString(1, giveDate);
                        count = 2;
                    }
                    case 2 -> {
                        String[] giveNameAndLvl = tempArray.toString()
                                .replace("select id from worker where name=","")
                                .replace("and level=","").trim()
                                .split(" ");
                        int number = 2;
                        for (String s : giveNameAndLvl) {
                            if(!s.equals("")) {
                                project_workerPrepared.setString(number, s);
                                number++;
                            }
                        }
                        project_workerPrepared.addBatch();
                        count = 1;
                    }
                }
            }
            project_workerPrepared.executeBatch();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            connection.close();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}

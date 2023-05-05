package Query;


import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService extends File {
    @SneakyThrows
    private static void populateWorkers(String sqlQuery, Connection connection) {
        try (PreparedStatement workerPrepared = connection
                .prepareStatement("insert into worker(name,birthday, level, salary) values (?,?,?,?)")) {
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
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            closedConnection(Database.INSTANCE.getConnection());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    private static void populateClients(String sqlQuery, Connection connection) {
        try (PreparedStatement clientPrepared = connection
                .prepareStatement("insert into client (name) values (?)")) {
            connection.setAutoCommit(false);
            Object[] clientArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into client(name)"));
            for (var tempArray : clientArray) {
                clientPrepared.setString(1, tempArray.toString().trim());
                clientPrepared.addBatch();
            }
            clientPrepared.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            closedConnection(Database.INSTANCE.getConnection());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    private static void populateProjects(String sqlQuery, Connection connection) {
        try (PreparedStatement projectPrepared = connection
                .prepareStatement("insert into project(client_id, starte_date, finish_date) values (select id from client where name=?,?,?)")) {
            connection.setAutoCommit(false);
            Object[] projectArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into project(client_id, starte_date, finish_date)"));
            int count = 1;
            for (var tempArray : projectArray) {
                switch (count) {
                    case 1 -> {
                        String giveName = tempArray.toString().replace("select id from client where name=", "").trim();
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
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            closedConnection(Database.INSTANCE.getConnection());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    private static void populateProjectAndWorkers(String sqlQuery, Connection connection) {
        try (PreparedStatement project_workerPrepared = connection
                .prepareStatement("insert into project_worker(project_id, worker_id) values (select id from project where starte_date=?,select id from worker where name=? and level=?)")) {
            connection.setAutoCommit(false);
            int count = 1;
            Object[] project_workerArray = createArray(getQueryFromFile(sqlQuery,
                    "insert into project_worker(project_id, worker_id)"));
            for (var tempArray : project_workerArray) {
                switch (count) {
                    case 1 -> {
                        String giveDate = tempArray.toString().replace("select id from project where starte_date=", "").trim();
                        project_workerPrepared.setString(1, giveDate);
                        count = 2;
                    }
                    case 2 -> {
                        String[] giveNameAndLvl = tempArray.toString()
                                .replace("select id from worker where name=", "")
                                .replace("and level=", "").trim()
                                .split(" ");
                        int number = 2;
                        for (String s : giveNameAndLvl) {
                            if (!s.equals("")) {
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
            closedConnection(Database.INSTANCE.getConnection());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private static void closedConnection(Connection connection){
        try (connection) {
            System.out.println("Connection is closed!");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = new DatabasePopulateService().readFile("src/main/resources/DB/populate_db.sql");
        populateWorkers(sql, Database.INSTANCE.getConnection());
        populateClients(sql, Database.INSTANCE.getConnection());
        populateProjects(sql, Database.INSTANCE.getConnection());
        populateProjectAndWorkers(sql, Database.INSTANCE.getConnection());
        closedConnection(Database.INSTANCE.getConnection());
    }
}

package query;

import labelClasses.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class DatabaseQueryService extends FileReader {
    public List<MaxSalaryWorker> findMaxSalaryWorker(Connection connection) {
        String sql = new DatabaseQueryService().readFile("src/main/resources/db/find_max_salary_worker.sql");
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
             connection) {

            List<MaxSalaryWorker> msw = new LinkedList<>();
            while(rs.next()){
                msw.add(new MaxSalaryWorker(rs.getString("name"),rs.getInt("max_salary")));
            }
            return msw;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxProjectsClient> findMaxProjectsClient(Connection connection) {
        String sql = new DatabaseQueryService().readFile("src/main/resources/db/find_max_projects_client.sql");
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
             connection) {
            List<MaxProjectsClient> mpc = new LinkedList<>();
            while(rs.next()){
                mpc.add(new MaxProjectsClient(rs.getString("name"),rs.getInt("max_project")));
            }
            return mpc;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LongestProject> findLongestProject(Connection connection) {
        String sql = new DatabaseQueryService().readFile("src/main/resources/db/find_longest_project.sql");
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
             connection) {
            List<LongestProject> lp = new LinkedList<>();
            while (rs.next()) {
                lp.add(new LongestProject(rs.getInt("id"), rs.getInt("month_count")));
            }
            return lp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers(Connection connection) {
        String sql = new DatabaseQueryService().readFile("src/main/resources/db/find_youngest_eldest_workers.sql");
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
             connection) {
            List<YoungestEldestWorkers> yew = new LinkedList<>();
            while(rs.next()){
                yew.add(new YoungestEldestWorkers(rs.getString("name"), LocalDate.parse(rs.getString("birthday")),
                        rs.getString("type")));
            }
            return yew;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProjectPrices> findProjectPrices(Connection connection) {
        String sql = new DatabaseQueryService().readFile("src/main/resources/db/print_project_prices.sql");
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
             connection) {
            List<ProjectPrices> yew = new LinkedList<>();
            while(rs.next()){
                yew.add(new ProjectPrices(rs.getInt("id"), rs.getInt("price")));
            }
            return yew;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker(Database.INSTANCE.getConnection());
        System.out.println(maxSalaryWorker + "\n");

        List<MaxProjectsClient> maxProjectsClient = new DatabaseQueryService().findMaxProjectsClient(Database.INSTANCE.getConnection());
        System.out.println(maxProjectsClient+ "\n");

        List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject(Database.INSTANCE.getConnection());
        System.out.println(longestProject+ "\n");

        List<YoungestEldestWorkers> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers(Database.INSTANCE.getConnection());
        System.out.println(youngestEldestWorkers+ "\n");

        List<ProjectPrices> projectPrices = new DatabaseQueryService().findProjectPrices(Database.INSTANCE.getConnection());
        System.out.println(projectPrices+ "\n");
    }
}

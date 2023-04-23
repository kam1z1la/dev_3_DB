package Query;

import LabelClasses.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class DatabaseQueryService extends File {
    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        String sql = new DatabaseQueryService().readFile("src/main/resources/DB/find_max_salary_worker.sql");
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            List<MaxSalaryWorker> msw = new LinkedList<>();
            while(rs.next()){
                msw.add(new MaxSalaryWorker(rs.getString("name"),rs.getInt("max_salary")));
            }
            return msw;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxProjectsClient> findMaxProjectsClient() {
        String sql = new DatabaseQueryService().readFile("src/main/resources/DB/find_max_projects_client.sql");
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            List<MaxProjectsClient> mpc = new LinkedList<>();
            while(rs.next()){
                mpc.add(new MaxProjectsClient(rs.getString("name"),rs.getInt("max_project")));
            }
            return mpc;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LongestProject> findLongestProject() {
        String sql = new DatabaseQueryService().readFile("src/main/resources/DB/find_longest_project.sql");
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            List<LongestProject> lp = new LinkedList<>();
            while(rs.next()){
                lp.add(new LongestProject(rs.getInt("id"),rs.getInt("month_count")));
            }
            return lp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        String sql = new DatabaseQueryService().readFile("src/main/resources/DB/find_youngest_eldest_workers.sql");
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
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

    public List<ProjectPrices> findProjectPrices() {
        String sql = new DatabaseQueryService().readFile("src/main/resources/DB/print_project_prices.sql");
        try (Connection connection = DriverManager.getConnection(Database.URL.getData(), Database.USER.getData(), Database.PASSWORD.getData());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
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
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        System.out.println(maxSalaryWorker + "\n");

        List<MaxProjectsClient> maxProjectsClient = new DatabaseQueryService().findMaxProjectsClient();
        System.out.println(maxProjectsClient+ "\n");

        List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject();
        System.out.println(longestProject+ "\n");

        List<YoungestEldestWorkers> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers();
        System.out.println(youngestEldestWorkers+ "\n");

        List<ProjectPrices> projectPrices = new DatabaseQueryService().findProjectPrices();
        System.out.println(projectPrices+ "\n");
    }
}

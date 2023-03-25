package services;

import Prefs.Prefs;
import Query_objects.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxSalaryWorker> findMaxSalaryWorker (Database database) {
        List<MaxSalaryWorker> listMaxSalaryWorker = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findMaxSalaryWorkerFilename = new Prefs().getString(Prefs.QUERY_FIND_MAX_SALARY_WORKER_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findMaxSalaryWorkerFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(name, salary);
                listMaxSalaryWorker.add(maxSalaryWorker);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return listMaxSalaryWorker;
    }

    public List<LongestProject> findLongestProject(Database database){
        List<LongestProject> listLongestProject = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findLongestProjectFileName = new Prefs().getString(Prefs.QUERY_FIND_LONGEST_PROJECT_SQL_FILE_PATH);
            String sql = String.join("\n",Files.readAllLines(Path.of(findLongestProjectFileName)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME");
                int monthCount = rs.getInt("MONTH_COUNT");

                LongestProject longestProject = new LongestProject(name,monthCount);
                listLongestProject.add(longestProject);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return listLongestProject;

    }

    public List<MaxProjectCountClient> findMaxProjectCountClient(Database database)  {
        List<MaxProjectCountClient> listMaxProjectCountClient = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findMaxProjectCountClientFileName = new Prefs().getString(Prefs.QUERY_FIND_MAX_PROJECTS_CLIENT_SQL_FILE_PATH);
            String sql = String.join("\n",Files.readAllLines(Path.of(findMaxProjectCountClientFileName)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("client.name");
                int projectCount = rs.getInt("PROJECT_COUNT");

                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient(name, projectCount);
                listMaxProjectCountClient.add(maxProjectCountClient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listMaxProjectCountClient;
    }

    public List<ProjectPrices> findProjectPrices(Database database) {
        List<ProjectPrices> listProjectPrices = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findProjectPricesFileName = new Prefs().getString(Prefs.QUERY_FIND_PROJECT_PRICES_SQL_FILE_PATH);
            String sql = String.join("\n",Files.readAllLines(Path.of(findProjectPricesFileName)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");
                ProjectPrices projectPrices = new ProjectPrices(name,price);
                listProjectPrices.add(projectPrices);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return listProjectPrices;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers(Database database) {
        List<YoungestEldestWorkers> listYoungestEldestWorkers = new ArrayList<>();
        try (Connection connection= database.getConnection()) {
            String findYoungestEldestWorkersFileName = new Prefs().getString(Prefs.QUERY_FIND_YOUNGEST_ELDEST_WORKERS_SQL_FILE_PATH);
            String sql = String.join("\n",Files.readAllLines(Path.of(findYoungestEldestWorkersFileName)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String type = rs.getString("TYPE");
                String name = rs.getString("NAME");
                Date date = rs.getDate("BIRTHDAY");

                YoungestEldestWorkers yongestEldestWorkers = new YoungestEldestWorkers(type,name,date);
                listYoungestEldestWorkers.add(yongestEldestWorkers);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return listYoungestEldestWorkers;
    }


}

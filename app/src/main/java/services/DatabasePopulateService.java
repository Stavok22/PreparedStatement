package services;

import Prefs.Prefs;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DatabasePopulateService {

    private final PreparedStatement insertWorker;
    private final PreparedStatement insertClient;
    private final PreparedStatement insertProject;
    private final PreparedStatement insertWorkerProject;

    private static Connection connection;

    public DatabasePopulateService(Database database) throws Exception {
        connection =database.getConnection();

        insertClient = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
        insertWorker = connection.prepareStatement("INSERT INTO worker (name,birthday,level,salary) VALUES (?,?,?,?)");
        insertProject = connection.prepareStatement("INSERT INTO project (client_id,start_date,finish_date) VALUES (?,?,?)");
        insertWorkerProject = connection.prepareStatement("INSERT INTO project_worker (project_id,worker_id) VALUES (?,?)");

    }

    public static void main(String[] args) throws Exception {
        Database database = Database.getInstanse();
        DatabasePopulateService databasePopulateService = new DatabasePopulateService(database);

        String populateWorker = new Prefs().getString(Prefs.POPULATE_WORKER_DB_FILE_PATH);
        List<String> workers = Files.readAllLines(Path.of(populateWorker));
        for (String str : workers) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewWorker(
                    strArray[0],
                    strArray[1],
                    strArray[2],
                    strArray[3]
            );
        }
        databasePopulateService.insertWorker.close();


        String populateClientDb = new Prefs().getString(Prefs.POPULATE_CLIENT_DB);
        List<String> clients = Files.readAllLines(Path.of(populateClientDb));
        for (String str: clients) {
            databasePopulateService.createNewClient(str);
        }
        databasePopulateService.insertClient.close();


        String populateProject = new Prefs().getString(Prefs.POPULATE_PROJECT_DB);
        List<String> projects = Files.readAllLines(Path.of(populateProject));
        for (String str: projects) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewProject(
                    strArray[0],
                    strArray[1],
                    strArray[2]
            );
        }
        databasePopulateService.insertProject.close();

        String populateProjectWorkerDb = new Prefs().getString(Prefs.POPULATE_PROJECT_WORKER_DB);
        List<String> projectWorker = Files.readAllLines(Path.of(populateProjectWorkerDb));
        for (String str : projectWorker) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewWorkerProject(
                    strArray[0],
                    strArray[1]
            );
        }
        databasePopulateService.insertWorkerProject.close();

        connection.close();
    }

    public void createNewWorker(String name, String birthday, String level, String salary) {
        try {
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday);
            insertWorker.setString(3, level);
            insertWorker.setString(4, salary);
            insertWorker.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createNewClient(String name) {
        try {
            insertClient.setString(1,name);
            insertClient.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createNewProject(String client_id,String start_date,String finish_date) {
        try{
            insertProject.setString(1,client_id);
            insertProject.setString(2,start_date);
            insertProject.setString(3,finish_date);
            insertProject.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void createNewWorkerProject(String project_id,String client_id) {
        try {
            insertWorkerProject.setString(1,project_id);
            insertWorkerProject.setString(2,client_id);
            insertWorkerProject.executeUpdate();

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}

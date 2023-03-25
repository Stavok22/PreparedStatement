package services;

import Prefs.Prefs;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseInitService {

    public static void main(String[] args) {
        Database database = Database.getInstanse();
        new DatabaseInitService().initDb(database);
    }

    public void initDb(Database database) {
        try {
            String initDbFilename = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(initDbFilename)));
            database.executeUpdate(sql);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}


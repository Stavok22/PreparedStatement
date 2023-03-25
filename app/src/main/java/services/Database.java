package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import Prefs.Prefs;


public class Database {
    private static  final Database INSTANSE = new Database();

    private Connection connection;

    private Database() {
        try {
            String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
            connection = DriverManager.getConnection(connectionUrl);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstanse() {
        return INSTANSE;
    }
    public int executeUpdate (String sql){
        try(Statement st=connection.createStatement()){
            return st.executeUpdate(sql);
        } catch (Exception exc) {
            exc.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection() throws Exception{
        return DriverManager.getConnection(new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL));
    }

    public void close() {
        try{
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package pl.krzysiek.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection;
    private Connection connection;
    private String host;
    private String username;
    private String password;

    private DatabaseConnection(){
            host = "jdbc:mysql://localhost:3306/ContactsEE";
            username = "root";
            password = "root";
    }

    public static DatabaseConnection getDatabaseConnection() {
        if(databaseConnection==null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() {

        try {
            if(connection==null || connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(host, username, password);
            }
        }catch(ClassNotFoundException fx){
            System.out.println("Class not found : " + fx.getMessage());
        } catch (SQLException ex) {
            System.out.println("Could not connect: " + ex.getMessage());
        }

        return connection;
    }


}

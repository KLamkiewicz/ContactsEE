package pl.krzysiek.dao;

import pl.krzysiek.model.AUser;
import pl.krzysiek.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by krzysiek on 24.01.15.
 */
public class AUserDAO {

    Connection conn;
    PreparedStatement p;

    public int createUser(AUser user){

        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("INSERT INTO AUser(login, haslo) VALUES(?, ?)");
            p.setString(1, user.getLogin());
            p.setString(2, user.getHaslo());
            return p.executeUpdate();
        }catch (Exception ex){
            System.out.println("Exception while adding user " + ex.getMessage());
        }

        return 0;
    }

    public boolean userExistsAlready(String login){
        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT * FROM AUser where login = ?");
            p.setString(1, login);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }

        }catch (Exception ex){
            System.out.println("Exeception checking user existence " + ex.getMessage());
        }

        return false;
    }
}

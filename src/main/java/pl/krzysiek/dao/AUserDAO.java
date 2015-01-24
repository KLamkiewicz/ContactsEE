package pl.krzysiek.dao;

import pl.krzysiek.model.AUser;
import pl.krzysiek.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }

        return 0;
    }

    public AUser getUser(String login, String password){
        AUser aUser = null;

        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT * FROM AUser WHERE login = ? AND haslo = ?");
            p.setString(1, login);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                aUser = new AUser();
                aUser.setLogin(rs.getString("login"));
                aUser.setUserId(rs.getInt("userId"));
            }
        }catch (Exception ex){
            System.out.println("Exeception getting user " + ex.getMessage());
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }

        return aUser;
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
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }

        return false;
    }
}

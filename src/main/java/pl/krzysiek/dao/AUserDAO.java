package pl.krzysiek.dao;

import pl.krzysiek.model.AUser;
import pl.krzysiek.model.Osoba;
import pl.krzysiek.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Osoba> getAUserContactsList(int id){
        List<Osoba> osoby = new ArrayList<Osoba>();

        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT * FROM Osoba WHERE ownerId = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Osoba o = new Osoba();
                o.setOsobaId(rs.getInt("OsobaId"));
                o.setImie(rs.getString("imie"));
                o.setNazwisko(rs.getString("nazwisko"));
                o.setEmail(rs.getString("email"));
                o.setTelefon(rs.getString("telefon"));
                o.setDob(rs.getDate("dob"));

                osoby.add(o);
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

        return  osoby;
    }

    public AUser getAUser(int id){
        AUser aUser = null;
        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT * FROM AUser WHERE userId = ?");
            p.setInt(1, id);
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

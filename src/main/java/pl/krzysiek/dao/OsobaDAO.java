package pl.krzysiek.dao;

import pl.krzysiek.model.Osoba;
import pl.krzysiek.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysiek on 23.01.15.
 */
public class OsobaDAO {

    Connection conn;
    PreparedStatement p;

    public int addOsoba(Osoba osoba, int id){
        int rid = 0;
        try {

            java.sql.Date sqlDate = new java.sql.Date(osoba.getDob().getTime());
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("INSERT INTO Osoba(imie, nazwisko, email, telefon, dob, ownerId) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            p.setString(1, osoba.getImie());
            p.setString(2, osoba.getNazwisko());
            p.setString(3, osoba.getEmail());
            p.setString(4, osoba.getTelefon());
            p.setDate(5, sqlDate);
            p.setInt(6, id);
            p.executeUpdate();

            ResultSet r = p.getGeneratedKeys();
            if(r.next()){
                rid = r.getInt(1);
                System.out.println(r.getInt(1));
            }

            return  rid;
        }catch (Exception ex){
            System.out.println("Exception while adding osoba " + ex.getMessage());
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }

        return rid;
    }

    public int addOsoba(Osoba osoba){

        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("INSERT INTO Osoba(imie, nazwisko, email) VALUES(?, ?, ?)");
            p.setString(1, osoba.getImie());
            p.setString(2, osoba.getNazwisko());
            p.setString(3, osoba.getEmail());

            return p.executeUpdate();
        }catch (Exception ex){
            System.out.println("Exception while adding osoba " + ex.getMessage());
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

    public List<Osoba> getAllOsoba(){
        List<Osoba> osobaList = new ArrayList<Osoba>();
        Osoba o = new Osoba();
        ResultSet resultSet;

        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT * FROM Osoba");
            resultSet = p.executeQuery();

            while (resultSet.next()){
                o.setImie(resultSet.getString("imie"));
                o.setNazwisko(resultSet.getString("nazwisko"));
                o.setOsobaId(resultSet.getInt("OsobaId"));
                o.setEmail(resultSet.getString("email"));
                o.setTelefon(resultSet.getString("telefon"));
                o.setDob(resultSet.getDate("dob"));

                osobaList.add(o);
                o = new Osoba();
            }
        }catch (Exception ex){
            System.out.println("Exeception getting Osoby " + ex.getMessage());
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }

        return osobaList;
    }

    public void deleteOsoba(int id){
        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("DELETE FROM Osoba where OsobaId = ?");
            p.setInt(1, id);
            p.executeUpdate();
        }catch (Exception ex){
            System.out.println("Exeception getting Osoby " + ex.getMessage());
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }
    }

    public boolean checkForEmail(int id, String email){
        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT email FROM Osoba WHERE ownerId = ? and email = ?");
            p.setInt(1, id);
            p.setString(2, email);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }

        }catch (Exception ex){
            System.out.println("Exception while checking for email " + ex.getMessage());
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

    public boolean contactBelongsToUser(int userId, int contactId){
        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("Select email FROM Osoba WHERE ownerId = ? and OsobaId = ?");
            p.setInt(1, userId);
            p.setInt(2, contactId);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }

        }catch (Exception ex){
            System.out.println("Exception while checking contact " + ex.getMessage());
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

    public boolean canUpdate(String email, int osobaId, int userId) {
        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("SELECT EXISTS(SELECT 1 FROM ContactsEE.Osoba WHERE OsobaId!=? AND email=? and  ownerId= ?)");
            p.setInt(1, osobaId);
            p.setString(2, email);
            p.setInt(3, userId);
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                //ResultSetMetaData rsmd = rs.getMetaData();
                //System.out.println(rs.getInt(rsmd.getColumnName(1)));
                return rs.getInt(1)==0?true:false;
            }
        }catch (Exception ex){
            System.out.println("Exception while checking if can update " + ex.getMessage());
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

    public boolean updateContact(Osoba o){
        boolean updated = false;

        try {

            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("UPDATE Osoba set imie=?, nazwisko=?, email=?, telefon=?, dob=? WHERE OsobaId = ?");
            p.setString(1, o.getImie());
            p.setString(2, o.getNazwisko());
            p.setString(3, o.getEmail());
            p.setString(4, o.getTelefon());
            p.setInt(6, o.getOsobaId());

            try {
                java.sql.Date sqlDate = new java.sql.Date(o.getDob().getTime());
                p.setDate(5, sqlDate);
            }catch (NullPointerException ex){
                p.setDate(5, null);
                System.out.println("No date " + ex);
            }

            return p.executeUpdate()!=0?true:false;

        }catch (Exception ex){
            System.out.println("Exception while updating contact " + ex.getMessage());
        }finally {
            try {
                if(conn!=null)
                    conn.close();
            }catch (Exception c){
                System.out.println(c);
            }
        }
        return updated;
    }

}

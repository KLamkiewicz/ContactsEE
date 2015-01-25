package pl.krzysiek.dao;

import pl.krzysiek.model.Osoba;
import pl.krzysiek.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysiek on 23.01.15.
 */
public class OsobaDAO {

    Connection conn;
    PreparedStatement p;

    public int addOsoba(Osoba osoba, int id){
        try {

            java.sql.Date sqlDate = new java.sql.Date(osoba.getDob().getTime());
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("INSERT INTO Osoba(imie, nazwisko, email, telefon, dob, ownerId) VALUES(?, ?, ?, ?, ?, ?)");
            p.setString(1, osoba.getImie());
            p.setString(2, osoba.getNazwisko());
            p.setString(3, osoba.getEmail());
            p.setString(4, osoba.getTelefon());
            p.setDate(5, sqlDate);
            p.setInt(6, id);

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
            p = conn.prepareStatement("Select email FROM Osoba WHERE ownerId = ? and email = ?");
            p.setInt(1, id);
            p.setString(2, email);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }

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

        return false;
    }

}

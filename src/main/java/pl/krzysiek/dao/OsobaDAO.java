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

    public void deleteOsoba(int id, int oid){
        try{
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("DELETE FROM Osoba where OsobaId = ? AND ownerId = ?");
            p.setInt(1, id);
            p.setInt(2, oid);
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

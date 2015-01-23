package pl.krzysiek.dao;

import pl.krzysiek.model.Osoba;
import pl.krzysiek.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by krzysiek on 23.01.15.
 */
public class OsobaDAO {

    Connection conn;

    public int addOsoba(Osoba osoba){
        PreparedStatement p = null;
        try {
            conn = DatabaseConnection.getDatabaseConnection().getConnection();
            p = conn.prepareStatement("INSERT INTO Osoba(imie, nazwisko) VALUES(?, ?)");
            p.setString(1, osoba.getImie());
            p.setString(2, osoba.getNazwisko());
            return p.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return 0;
    }
}

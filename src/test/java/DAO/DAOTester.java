package DAO;

import org.junit.Test;
import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.Osoba;
import pl.krzysiek.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

/**
 * Created by krzysiek on 23.01.15.
 */

public class DAOTester {

    private DatabaseConnection dbconn = DatabaseConnection.getDatabaseConnection();
    private OsobaDAO osobaDAO = new OsobaDAO();
    private String imie = "Adam";
    private String nazwisko = "Kowalski";

    @Test
    public void connectionTest(){
        Connection con = dbconn.getConnection();
        try {
            System.out.println(con);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Test
    public void addOsoba(){
        Connection con = dbconn.getConnection();
        Osoba o = new Osoba();
        o.setImie(imie);
        o.setNazwisko(nazwisko);
        osobaDAO = new OsobaDAO();

        assertEquals(1, osobaDAO.addOsoba(o));
    }
}

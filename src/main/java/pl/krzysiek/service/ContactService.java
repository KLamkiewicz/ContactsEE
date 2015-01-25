package pl.krzysiek.service;

import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.AUser;
import pl.krzysiek.model.Osoba;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by krzysiek on 25.01.15.
 */
public class ContactService {
    private OsobaDAO osobaDAO;

    public  ContactService(OsobaDAO osobaDAO){
        this.osobaDAO = osobaDAO;
    }

    public boolean checkForEmail(int id, HttpServletRequest request){
        String email = request.getParameter("email");
        return  osobaDAO.checkForEmail(id, email);

    }

    public int addUser(HttpServletRequest request, int id){
        String email = request.getParameter("email");
        String imie = request.getParameter("name");
        String nazwisko = request.getParameter("surname");
        String telefon = request.getParameter("phone");
        String date = request.getParameter("birth");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String dateInString = date ;
        Date d;
        Osoba o = new Osoba();
        o.setImie(imie);
        o.setNazwisko(nazwisko);
        o.setEmail(email);
        o.setTelefon(telefon);

        try {
            d = sdf.parse(dateInString);
            o.setDob(d);
            System.out.println(d);
        }catch (Exception e){
            System.out.println(e);
        }

        return osobaDAO.addOsoba(o, id);
    }
}

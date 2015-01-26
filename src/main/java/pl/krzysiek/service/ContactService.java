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

    public  ContactService(){
        osobaDAO = new OsobaDAO();
    }

    public boolean checkForEmail(int id, HttpServletRequest request){
        String email = request.getParameter("email");
        return  osobaDAO.checkForEmail(id, email);

    }

    public boolean contactBelongsToUser(int id, int cid){
        return osobaDAO.contactBelongsToUser(id, cid);
    }

    public boolean updateUser(HttpServletRequest request, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date d;
        AUser aUser = (AUser) request.getSession().getAttribute("user");
        int userId = aUser.getUserId();
        Osoba o = new Osoba();
        o.setOsobaId(Integer.parseInt(request.getParameter("contactId")));
        o.setEmail(request.getParameter("email"));
        boolean can = osobaDAO.canUpdate(o.getEmail(), o.getOsobaId(), userId);
        if(!can){
            return false;
        }

        o.setImie(request.getParameter("imie"));
        o.setTelefon(request.getParameter("telefon"));
        o.setEmail(request.getParameter("email"));
        o.setNazwisko(request.getParameter("nazwisko"));
        try {
            d = sdf.parse(request.getParameter("dob"));
            o.setDob(d);
        }catch (Exception e){
            System.out.println("No date available for editContact update parsee " + e);
        }

        osobaDAO.updateContact(o);

        return true;
    }

    public int addContact(HttpServletRequest request, int id){
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
            System.out.println("No date available " + e);
        }

        return osobaDAO.addOsoba(o, id);
    }
}

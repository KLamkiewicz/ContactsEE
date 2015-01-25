package pl.krzysiek.model;

import java.util.Date;

/**
 * Created by krzysiek on 23.01.15.
 */

public class Osoba {

    private int OsobaId;
    private String imie;
    private String nazwisko;
    private String email;
    private String telefon;
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getOsobaId() {
        return OsobaId;
    }

    public void setOsobaId(int OsobaId) {
        this.OsobaId = OsobaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "OsobaId=" + OsobaId +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}

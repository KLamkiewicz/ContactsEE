package pl.krzysiek.model;

/**
 * Created by krzysiek on 23.01.15.
 */

public class Osoba {

    private int OsobaId;
    private String imie;
    private String nazwisko;


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

    @Override
    public String toString() {
        return "Osoba{" +
                "OsobaId=" + OsobaId +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}

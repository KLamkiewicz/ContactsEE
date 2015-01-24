package pl.krzysiek.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysiek on 23.01.15.
 */
public class AUser {

    private int userId;
    private String login;
    private String haslo;
    private List<Osoba> contacts = new ArrayList<Osoba>();

    public AUser(){

    }
    public AUser(String login, String haslo){
        this.login = login;
        this.haslo = haslo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setContacts(List<Osoba> contacts) {
        this.contacts = contacts;
    }

    public List<Osoba> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "AUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }
}

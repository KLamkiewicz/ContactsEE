package pl.krzysiek.model;

/**
 * Created by krzysiek on 23.01.15.
 */
public class AUser {

    private int userId;
    private String login;
    private String haslo;

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
}

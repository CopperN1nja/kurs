package models;

public class AccList {
    private int id_user;
    private String login;
    private String password;
    private int role;

    public AccList(int id_user, String login, String password, int role) {
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

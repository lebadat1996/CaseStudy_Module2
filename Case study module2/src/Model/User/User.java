package Model.User;

import java.io.Serializable;

public class User implements Serializable {
    private String user;
    private String Password;
    private String rePassword;

    public String getRePassword() {
        return rePassword;
    }

    public String setRePassword(String rePassword) {
        this.rePassword = rePassword;
        return rePassword;
    }

    public User() {

    }

    public User(String name, String pass, String rePassword) {
        this.user = name;
        this.Password = pass;
        this.rePassword = rePassword;
    }

    public String getUser() {
        return user;
    }

    public String setUser(String user) {
        this.user = user;
        return user;
    }

    public String getPassword() {
        return Password;
    }

    public String setPassword(String password) {
        Password = password;
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}

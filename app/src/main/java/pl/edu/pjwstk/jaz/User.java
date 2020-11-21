package pl.edu.pjwstk.jaz;


import java.util.Set;

public class User {

    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Set<String> getAuthorities() {

        return null;
       // return getAuthorities();
    }
}

package pl.edu.pjwstk.jaz;


import java.util.Set;

public class User {

    private final String username;
    private final String password;
    private final Set<String> authorities;
    private final String firstName;
    private final String lastName;

    public User(String username, String password, Set<String> authorities, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public Set<String> getAuthorities() {
        return authorities;
    }

    public void updatedAuthority(String authorities) {
        this.authorities.add(authorities);
    }

}

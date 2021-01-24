package pl.edu.pjwstk.jaz.request;

import java.util.Collections;

public class RegisterRequest {

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterRequest(String username, String password,String firstName,String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }








}

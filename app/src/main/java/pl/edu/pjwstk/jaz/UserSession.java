package pl.edu.pjwstk.jaz;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    private boolean isLogged = false;

    public void logIn() {
        isLogged = true;
    }

    public boolean isLoggedIn() {

        return isLogged;
    }
    //tutaj jakas zmienna informacja

    //ktora pozwoli okreslic czy uzytkownik jest zalogowany czy nie
    // metody do zarzadzania ta informacja
}

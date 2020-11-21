package pl.edu.pjwstk.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationService {

    RegisterController registerController = new RegisterController();
    final UserSession userSession;

    public AuthenticationService(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {

        HashMap<Integer, User> users = registerController.getUsersMap();

        for (Map.Entry<Integer, User> usersEntry : users.entrySet()) {

            User userMapEntrySet = usersEntry.getValue();
            if(userMapEntrySet.getUsername().equals(username) && userMapEntrySet.getPassword().equals(password)) {
                userSession.logIn();

              var user = new User(username, password);// tutaj wrzucic hashmape


                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
                return true;
            }

        }
        return false;
        //return users.containsKey(username) && users.containsValue(password);
        //return users.equals(username) && users.equals(password);
    }

}

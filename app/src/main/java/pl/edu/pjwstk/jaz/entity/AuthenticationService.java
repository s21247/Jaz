package pl.edu.pjwstk.jaz.entity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.authentication.AppAuthentication;
import pl.edu.pjwstk.jaz.entity.UserEntity;
import pl.edu.pjwstk.jaz.entity.UserService;
import pl.edu.pjwstk.jaz.UserSession;
import pl.edu.pjwstk.jaz.controller.RegisterController;


@Component
public class AuthenticationService {


    private final UserService userService;
    RegisterController registerController;
    final UserSession userSession;
    private UserEntity userEntity;
     PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthenticationService(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {

        UserEntity userDB = userService.findUserByUsername(username);

        if(userDB.getUsername().equals(username) && passwordEncoder.matches(password,userDB.getPassword())) {
            userSession.logIn();
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userDB));
            return true;
        }
        return false;

//        HashMap<String, User> users = registerController.getUsersMap();
//
//        for (Map.Entry<String, User> usersEntry : users.entrySet()) {
//
//            User userMapEntrySet = usersEntry.getValue();
//            if(userMapEntrySet.getUsername().equals(username) && userMapEntrySet.getPassword().equals(password)) {
//                String keyValue = usersEntry.getKey();
//
//                userSession.logIn();
//
//                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(users.get(keyValue)));
//                return true;
//            }
//        }
   }

}

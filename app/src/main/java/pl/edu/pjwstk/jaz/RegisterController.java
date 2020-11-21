package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class  RegisterController {

    public static HashMap<Integer,User> users = new HashMap<Integer, User>();
    public HashMap<Integer,User> getUsersMap(){
        return users;
    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        //todo czy uzytkownik istnieje
            users.put(users.size() + 1, new User(registerRequest.getUsername(), registerRequest.getPassword()));
            //users.put(registerRequest.getUsername(),registerRequest.getPassword());
            System.out.println(users);

        //registerUser
    }
    }


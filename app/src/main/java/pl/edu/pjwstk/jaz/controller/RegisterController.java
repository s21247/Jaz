package pl.edu.pjwstk.jaz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.request.RegisterRequest;
import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.entity.UserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@RestController
public class  RegisterController {
    private final UserService userService;

    public static HashMap<String, User> users = new HashMap<>();

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    public HashMap<String,User> getUsersMap(){
        return users;
    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        Set<String> authorities = new HashSet<>();

        authorities.add("User");
//        userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),authorities,
//                registerRequest.getFirstName(),registerRequest.getLastName());
            userService.saveUser(registerRequest);

    }
}


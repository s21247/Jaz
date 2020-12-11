package pl.edu.pjwstk.jaz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@RestController
public class  RegisterController {
    private final UserService userService;

    public static HashMap<String,User> users = new HashMap<>();

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    public HashMap<String,User> getUsersMap(){
        return users;
    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        Set<String> authorities = new HashSet<>();
        AuthenticationEntity authenticationEntity = new AuthenticationEntity();
        authenticationEntity.setAuthority("Admin");

//        if(users.isEmpty())
//            authorities.add("Admin");
//
//        if(users.containsKey(registerRequest.getUsername()))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

       // User user = new User(registerRequest.getUsername(),registerRequest.getPassword(),authorities);
        //users.put(registerRequest.getUsername(),user);

        userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),authorities);

    }
}


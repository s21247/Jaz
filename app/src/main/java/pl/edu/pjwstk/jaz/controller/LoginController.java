package pl.edu.pjwstk.jaz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.entity.AuthenticationService;
import pl.edu.pjwstk.jaz.request.LoginRequest;
import pl.edu.pjwstk.jaz.UnauthorizedException;

@RestController
public class LoginController {
    private final AuthenticationService authenticationService;

    private String controller;
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        //todo uwierzytelnic
        var isLogged = authenticationService.login(loginRequest.getUsername(),loginRequest.getPassword());
        if(!isLogged) {
            throw new UnauthorizedException();
        }
        System.out.println(loginRequest.getUsername());
        controller = loginRequest.getUsername();
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}


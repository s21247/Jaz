package pl.edu.pjwstk.jaz;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
public class GrantedAuthority {

    UserService userService;
    RegisterController registerController = new RegisterController(userService);


    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("Granted")
    public void giveAuthority(@RequestBody RegisterRequest registerRequest) {
       HashMap<String,User> users = registerController.getUsersMap();

       User newManager = users.get(registerRequest.getUsername());

       newManager.updatedAuthority("Menager");
    }

    @PreAuthorize("hasAnyAuthority('Menager')")
    @GetMapping("Menager")
    public void gotAuthority() {
        System.out.println("Brawo masz uprawnienia");

    }
}

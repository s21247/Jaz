package pl.edu.pjwstk.jaz;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Set;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
public class GrantedAuthority {

    final UserService userService;
    final RegisterController registerController;
    final EntityManager entityManager;
    public GrantedAuthority(UserService userService, RegisterController registerController, EntityManager entityManager) {
        this.userService = userService;
        this.registerController = registerController;
        this.entityManager = entityManager;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("Granted")
    @Transactional
    public void giveAuthority(@RequestBody RegisterRequest registerRequest) {
      // HashMap<String,User> users = registerController.getUsersMap();

      var  newMenager = userService.findUserByUsername(registerRequest.getUsername());
        Set<String> authorities = newMenager.getAuthorities();
        authorities.add("Menager");
        newMenager.setAuthorities(authorities);
        entityManager.merge(newMenager);

        //newManager.updatedAuthority("Menager");
    }

    @PreAuthorize("hasAnyAuthority('Menager')")
    @GetMapping("Menager")
    public void gotAuthority() {
        System.out.println("Brawo masz uprawnienia");

    }
}

package pl.edu.pjwstk.jaz.entity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.entity.UserEntity;
import pl.edu.pjwstk.jaz.request.RegisterRequest;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserService {
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(RegisterRequest registerRequest) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
      String  password = passwordEncoder.encode(registerRequest.getPassword());
        userEntity.setPassword(password);
        Set<String> authorities = new HashSet<>();
        authorities.add("User");
        if(userEntity.getUsername().equals("wojtek"))
            authorities.add("Admin");

        userEntity.setAuthorities(authorities);
        userEntity.setFirstname(registerRequest.getFirstName());
        userEntity.setLastname(registerRequest.getLastName());
        entityManager.persist(userEntity);

    }

    public UserEntity findUserByUsername(String username) {
      return  entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username",username) // username?
                .getSingleResult();
    }
}

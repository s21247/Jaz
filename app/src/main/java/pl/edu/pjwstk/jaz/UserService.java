package pl.edu.pjwstk.jaz;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@Repository
public class UserService {
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(String username, String password, Set<String> authorities) {
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        password = passwordEncoder.encode(password);
        userEntity.setPassword(password);
        userEntity.setAuthorities(authorities);
        entityManager.persist(userEntity);
        if(userEntity.getId() == 1) {
            authorities.add("Admin");
        }else
        authorities.add("default");
        //userEntity.getId() > 0
    }

    public UserEntity findUserByUsername(String username) {
      return  entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username",username) // username?
                .getSingleResult();
    }
}

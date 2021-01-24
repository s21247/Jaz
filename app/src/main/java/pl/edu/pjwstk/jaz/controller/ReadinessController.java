package pl.edu.pjwstk.jaz.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
public class ReadinessController {

    @PersistenceContext
   private final EntityManager em;

    public ReadinessController(EntityManager em) {
        this.em = em;
    }
    @PreAuthorize("hasAnyAuthority('Admin')")
    @GetMapping("auth0/is-ready")
    @Transactional

    public void readinessTest() {
//        var entity = new Test1Entity();
//        entity.setName("sdavsda");
//        em.persist(entity);
    }
}

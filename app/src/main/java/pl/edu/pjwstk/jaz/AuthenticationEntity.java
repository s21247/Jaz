package pl.edu.pjwstk.jaz;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthenticationEntity {
        @Id
        @Column(name = "authorities_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String authority;

        public Long getId() {
            return id;
        }

        public String getAuthority() {
            return authority;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
}

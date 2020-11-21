package pl.edu.pjwstk.jaz.readiness;

import javax.persistence.*;

@Entity
@Table(name = "test1")
public class Test1Entity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

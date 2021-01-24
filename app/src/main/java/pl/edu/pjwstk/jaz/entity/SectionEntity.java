package pl.edu.pjwstk.jaz.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "section")
public class SectionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String title;

    @OneToMany
    @JoinColumn(name = "section_id")
    private Set<CategoryEntity> categories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

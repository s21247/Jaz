package pl.edu.pjwstk.jaz.entity;

import pl.edu.pjwstk.jaz.request.ParameterRequest;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String key;

    @OneToMany(mappedBy = "parameterEntity", cascade = CascadeType.ALL)
    private Set<Auction_parameterEntity> parameter_auction;

    public ParameterEntity() {
    }

    public ParameterEntity(String key, Set<Auction_parameterEntity> parameter_auction) {
        this.key = key;
        this.parameter_auction = parameter_auction;
    }

    public long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<Auction_parameterEntity> getParameter_auction() {
        return parameter_auction;
    }

    public void setParameter_auction(Set<Auction_parameterEntity> parameter_auction) {
        this.parameter_auction = parameter_auction;
    }
}

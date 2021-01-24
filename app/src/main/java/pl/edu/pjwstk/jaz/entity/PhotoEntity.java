package pl.edu.pjwstk.jaz.entity;

import pl.edu.pjwstk.jaz.request.PhotoRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String link;

    @OrderBy
    @Column(name = "\"order\"")
    private int order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionEntity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }
}

package pl.edu.pjwstk.jaz.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "auction_view")
@Immutable
public class AuctionView implements Serializable {

    @Id
    @Column(name = "auction_id")
    private Long auction_id;

    @Column(name = "auctiontitle")
    private String auctionTitle;
    @Column(name = "price")
    private int price;
    @Column(name = "miniature")
    private String miniature;

    public AuctionView() {
    }

    public AuctionView(Long auction_id, String auctionTitle, int price, String miniature) {
        this.auction_id = auction_id;
        this.auctionTitle = auctionTitle;
        this.price = price;
        this.miniature = miniature;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMiniature() {
        return miniature;
    }

    public void setMiniature(String miniature) {
        this.miniature = miniature;
    }
}

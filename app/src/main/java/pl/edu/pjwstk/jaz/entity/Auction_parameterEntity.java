package pl.edu.pjwstk.jaz.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auction_parameter")
public class Auction_parameterEntity {

    @EmbeddedId
    private Auction_parameterKey id = new Auction_parameterKey();

    @ManyToOne
    @MapsId("parameter_id")
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameterEntity;

    @ManyToOne
    @MapsId("auction_id")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionEntity;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Auction_parameterKey getId() {
        return id;
    }

    public void setId(Auction_parameterKey id) {
        this.id = id;
    }

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }
}

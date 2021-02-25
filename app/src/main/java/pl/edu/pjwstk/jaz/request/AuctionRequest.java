package pl.edu.pjwstk.jaz.request;

import java.util.List;

public class AuctionRequest {
    private String description;
    private int price;
    private String title;
    private Long version;

    private List<PhotoRequest> photo;
    private List<ParameterRequest> parameter;

    private String category;

    private String owner;


    public AuctionRequest(String description, int price, String title, Long version, List<PhotoRequest> photo, List<ParameterRequest> parameter,
                          String category) {
        this.description = description;
        this.price = price;
        this.title = title;
        this.version = version;
        this.photo = photo;
        this.parameter = parameter;
        this.category = category;
    }

    public AuctionRequest() {
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<PhotoRequest> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoRequest> photo) {
        this.photo = photo;
    }

    public List<ParameterRequest> getParameter() {
        return parameter;
    }

    public void setParameter(List<ParameterRequest> parameter) {
        this.parameter = parameter;
    }
}

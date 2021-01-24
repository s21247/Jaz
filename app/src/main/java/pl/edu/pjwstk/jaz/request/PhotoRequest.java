package pl.edu.pjwstk.jaz.request;

public class PhotoRequest {

    private String link;
    private int order;

    public PhotoRequest(String link, int order) {
        this.link = link;
        this.order = order;

    }

    public PhotoRequest() {
    }


    public String getLink() {
        return link;
    }

    public int getOrder() {
        return order;
    }


}

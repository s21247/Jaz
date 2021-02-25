package pl.edu.pjwstk.jaz.request;

public class CategoryRequest {

    private String title;

    private String sectionTitle;

    public CategoryRequest(String title) {
        this.title = title;
    }

    public CategoryRequest() {
    }

    public CategoryRequest(String title, String sectionTitle) {
        this.title = title;
        this.sectionTitle = sectionTitle;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

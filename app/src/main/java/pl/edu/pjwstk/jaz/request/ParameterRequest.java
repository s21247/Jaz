package pl.edu.pjwstk.jaz.request;

public class ParameterRequest {

    private String value;
    private String key;

    public ParameterRequest(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public ParameterRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

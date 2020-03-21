package de.letsdev.products.dico.scanner.backend;

public class Test {

    private long id;
    private String timestamp;
    private String state;
    private String result;

    public Test(long id, String timestamp, String state, String result) {
        this.id = id;
        this.timestamp = timestamp;
        this.state = state;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

package de.letsdev.products.dico.scanner.backend;

public class Test {

    private String timestamp;
    private String state;
    private String result;

    public Test(String timestamp, String state, String result) {
        this.timestamp = timestamp;
        this.state = state;
        this.result = result;
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

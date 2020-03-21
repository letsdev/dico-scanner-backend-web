package de.letsdev.products.dico.scanner.backend;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", state='" + state + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                Objects.equals(timestamp, test.timestamp) &&
                Objects.equals(state, test.state) &&
                Objects.equals(result, test.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, state, result);
    }
}

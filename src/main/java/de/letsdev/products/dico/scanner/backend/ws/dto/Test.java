package de.letsdev.products.dico.scanner.backend.ws.dto;

import de.letsdev.products.dico.scanner.backend.db.TestState;

import java.util.Objects;

public class Test {

    private long id;
    private String timestamp;
    private TestState.State result;

    public Test(long id, String timestamp, TestState.State state) {
        this.id = id;
        this.timestamp = timestamp;
        this.result = state;
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

    public TestState.State getResult() {
        return result;
    }

    public void setState(TestState.State result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
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
                Objects.equals(result, test.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, result);
    }
}

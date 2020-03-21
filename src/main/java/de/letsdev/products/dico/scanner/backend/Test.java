package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.TestState;

import java.util.Objects;

public class Test {

    private long id;
    private String timestamp;
    private TestState.State state;

    public Test(long id, String timestamp, TestState.State state) {
        this.id = id;
        this.timestamp = timestamp;
        this.state = state;
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

    public TestState.State getState() {
        return state;
    }

    public void setState(TestState.State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                Objects.equals(timestamp, test.timestamp) &&
                Objects.equals(state, test.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, state);
    }
}

package de.letsdev.products.dico.scanner.backend.db;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TestState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp timestamp;

    private State state;

    private String result;

    @ManyToOne
    private Device device;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public static enum State {
        INITIALIZED("initialized"),
        IS_POSITIVE("positive"),
        IS_NEGATIVE("negative");

        final String value;

        State(String value) {
            this.value = value;
        }
    }
}

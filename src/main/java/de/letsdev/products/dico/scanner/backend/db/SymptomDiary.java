package de.letsdev.products.dico.scanner.backend.db;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SymptomDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp timestamp;

    @ManyToOne
    private Device device;

    @OneToMany
    private List<de.letsdev.products.dico.scanner.backend.db.Symptom> symptoms = new ArrayList<>();

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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymptomDiary that = (SymptomDiary) o;
        return id == that.id &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(device, that.device) &&
                Objects.equals(symptoms, that.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, device, symptoms);
    }

    @Override
    public String toString() {
        return "SymptomDiary{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", device=" + device +
                ", symptoms=" + symptoms +
                '}';
    }
}

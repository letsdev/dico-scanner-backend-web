package de.letsdev.products.dico.scanner.backend.ws.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SymptomDiary {

    private String timestamp;

    private List<Symptom> symptoms = new ArrayList<>();

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(symptoms, that.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, symptoms);
    }

    @Override
    public String toString() {
        return "SymptomDiary{" +
                "timestamp='" + timestamp + '\'' +
                ", symptoms=" + symptoms +
                '}';
    }
}

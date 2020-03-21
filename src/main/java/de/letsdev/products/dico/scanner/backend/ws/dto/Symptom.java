package de.letsdev.products.dico.scanner.backend.ws.dto;

import java.util.Objects;

public class Symptom {

    private Integer id;

    private String nameDe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(id, symptom.id) &&
                Objects.equals(nameDe, symptom.nameDe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDe);
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", nameDe='" + nameDe + '\'' +
                '}';
    }
}

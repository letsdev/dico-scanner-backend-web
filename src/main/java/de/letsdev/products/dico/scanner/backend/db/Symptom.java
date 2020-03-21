package de.letsdev.products.dico.scanner.backend.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return "Symtom{" +
                "id=" + id +
                ", nameDe='" + nameDe + '\'' +
                '}';
    }
}

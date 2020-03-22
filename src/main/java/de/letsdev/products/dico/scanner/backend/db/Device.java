package de.letsdev.products.dico.scanner.backend.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String uuid;

    @OneToMany
    @JoinColumn(name = "device_id")
    private Set<Location> locations;

    @OneToMany
    @JoinColumn(name = "device_id")
    private Set<TestState> testStates;

    public void setId(long id) {

        this.id = id;
    }

    public void setUuid(String uuid) {

        this.uuid = uuid;
    }

    public void setLocations(Set<Location> locations) {

        this.locations = locations;
    }

    public long getId() {

        return id;
    }

    public String getUuid() {

        return uuid;
    }

    public Set<Location> getLocations() {

        return locations;
    }

    public Set<TestState> getTestStates() {

        return testStates;
    }

    public void setTestStates(Set<TestState> testStates) {

        this.testStates = testStates;
    }
}
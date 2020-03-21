package de.letsdev.products.dico.scanner.backend.db;

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
    long id;

    String uuid;

    @OneToMany
    @JoinColumn(name = "location_id")
    Set<Location> locations;
}
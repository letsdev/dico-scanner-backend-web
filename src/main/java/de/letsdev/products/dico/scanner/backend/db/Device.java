package de.letsdev.products.dico.scanner.backend.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String uuid;
}

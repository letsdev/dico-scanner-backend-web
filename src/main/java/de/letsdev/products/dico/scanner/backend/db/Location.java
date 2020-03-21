package de.letsdev.products.dico.scanner.backend.db;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Timestamp;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Timestamp timestamp;

    float lon;

    float lat;

    float accuracy;

    @ManyToOne
    Device device;

}

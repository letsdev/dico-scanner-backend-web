package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    Device device;

}

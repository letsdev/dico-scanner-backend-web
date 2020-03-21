package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Timestamp timestamp;

    float lon;

    float lat;

    float accuracy;

}

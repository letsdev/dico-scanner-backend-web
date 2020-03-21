package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class Location {

    @Id
    Timestamp timestamp;

    float lon;

    float lat;

    float accuracy;

}

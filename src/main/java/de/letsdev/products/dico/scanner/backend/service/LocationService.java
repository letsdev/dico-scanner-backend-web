package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void savePosition(Timestamp timestamp, Location){


    }

}

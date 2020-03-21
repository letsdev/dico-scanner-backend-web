package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void savePosition(String deviceID, Position position) {
        Instant instant = Instant.parse(position.getTimestamp());
        Timestamp timestamp = Timestamp.from(instant);

        // ToDo: save in locationRepository
    }

}
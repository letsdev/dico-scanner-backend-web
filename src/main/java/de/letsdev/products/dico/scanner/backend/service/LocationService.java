package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void savePosition(Device device, Position position) {
        Instant instant = Instant.parse(position.getTimestamp());
        Timestamp timestamp = Timestamp.from(instant);

        Location location = new Location();
        location.setTimestamp(timestamp);
        location.setLon(position.getLon());
        location.setLat(position.getLat());
        location.setAccuracy(position.getAccuracy());
        location.setDevice(device);
        locationRepository.save(location);
    }

    public List<Position> findAllByDeviceUuid(String uuid) {
        return locationRepository.findAllByDeviceUuid(uuid);
    }
}

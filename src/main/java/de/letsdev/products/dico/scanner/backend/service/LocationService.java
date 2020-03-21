package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public void savePosition(Device device, Position position) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(position.getTimestamp(), formatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Location location = new Location();
        location.setTimestamp(timestamp);
        location.setLon(position.getLon());
        location.setLat(position.getLat());
        location.setAccuracy(position.getAccuracy());
        location.setDevice(device);
        locationRepository.save(location);
    }

    public List<Position> findAllByDeviceUuid(String uuid) {

        List<Location> locations = locationRepository.findAllByDeviceUuid(uuid);
        List<Position> positions = new ArrayList<Position>();

        for (Location location : locations) {
            Position position = new Position();
            position.setLon(location.getLon());
            position.setLat(location.getLat());
            position.setAccuracy(location.getAccuracy());
            position.setTimestamp(location.getTimestamp().toString());
            positions.add(position);
        }

        return positions;
    }
}

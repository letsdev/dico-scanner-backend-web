package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private Environment environment;

    public Location savePosition(Device device, Position position) {

        Instant instant = Instant.parse(position.getTimestamp());
        Timestamp timestamp = Timestamp.from(instant);

        Location location = new Location();
        location.setTimestamp(timestamp);
        location.setLon(position.getLon());
        location.setLat(position.getLat());
        location.setAccuracy(position.getAccuracy());
        location.setDevice(device);
        locationRepository.save(location);

        return location;
    }

    public List<Position> findAllByDeviceUuid(String uuid) {

        List<Location> locations = locationRepository.findAllByDeviceUuid(uuid);
        List<Position> positions = new ArrayList<>();

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

    @Async
    public CompletableFuture<List<Location>> findNearlyLocations(Location location) {
        //TODO get allLocations withing 14 day (config value)
        String maxDays = environment.getProperty("coscan.search.maximum.days");
        int max = Integer.parseInt(maxDays);

        Calendar cal = Calendar.getInstance();
        cal.setTime(location.getTimestamp());
        cal.add(Calendar.DAY_OF_WEEK, -max);
        Timestamp referenceTimestamp = new Timestamp(cal.getTime().getTime());

        List<Location> locations = locationRepository.findAllByTimestampAfter(referenceTimestamp);

        double latFrom = location.getLat();
        double lonFrom = location.getLon();

        return null;
    }
}

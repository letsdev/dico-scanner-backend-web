package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import de.letsdev.products.dico.scanner.backend.helper.DistanceHelper;
import de.letsdev.products.dico.scanner.backend.helper.TimestampConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    private static final String DEFAULT_VALUE_MAX_DAYS = "14";
    private static final String DEFAULT_VALUE_BETWEEN_TIME = "30";
    private static final String DEFAULT_VALUE_MAX_DISTANCE = "200";

    public Location savePosition(Device device, Position position) {

        Location location = new Location();
        location.setTimestamp(TimestampConverter.convertStringToTimestamp(position.getTimestamp()));
        location.setLon(position.getLon());
        location.setLat(position.getLat());
        location.setAccuracy(position.getAccuracy());
        location.setDevice(device);
        locationRepository.save(location);

        return location;
    }

    public List<Position> findAllByDeviceUuid(String uuid) {

        return convertList(locationRepository.findAllByDeviceUuid(uuid));

    }

    public List<Position> findAll() {

        return convertList(locationRepository.findAll());

    }

    private List<Position> convertList(List<Location> list) {
        List<Position> positions = new ArrayList<Position>(list.size());

        for (Location location : list) {
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

//        String maxDaysConfig = environment.getProperty("coscan.search.maximum.days", DEFAULT_VALUE_MAX_DAYS);
//        int maxDays = Integer.parseInt(maxDaysConfig);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(location.getTimestamp());
//        cal.add(Calendar.DAY_OF_WEEK, -maxDays);
//        Timestamp referenceTimestamp = new Timestamp(cal.getTime().getTime());


        String searchBetweenTimeConfig = environment.getProperty("coscan.search.between.minutes", DEFAULT_VALUE_BETWEEN_TIME);
        int searchBetweenTime = Integer.parseInt(searchBetweenTimeConfig);

        Calendar cal = Calendar.getInstance();
        cal.setTime(location.getTimestamp());
        cal.add(Calendar.MINUTE, -searchBetweenTime);
        Timestamp referenceTimestampBefore = new Timestamp(cal.getTime().getTime());
        cal.add(Calendar.MINUTE, 2 * searchBetweenTime);
        Timestamp referenceTimestampAfter = new Timestamp(cal.getTime().getTime());

        //TODO fetch only positive co
        List<Location> locations = locationRepository.findAllByTestResultPositiveAndTimestampBetweenAnd(referenceTimestampBefore, referenceTimestampAfter);

        double latFrom = location.getLat();
        double lonFrom = location.getLon();

        String maxMetersConfig = environment.getProperty("coscan.search.area.alert.max.distance.meters", DEFAULT_VALUE_MAX_DISTANCE);
        int maxMeters = Integer.parseInt(maxMetersConfig);

        for (Location loc : locations) {
            DistanceHelper.isDistanceBiggerThanReference(latFrom, loc.getLat(), lonFrom, loc.getLon(), maxMeters);
        }

        return null;
    }
}

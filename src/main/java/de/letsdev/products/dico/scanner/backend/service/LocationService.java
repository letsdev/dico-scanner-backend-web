package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import de.letsdev.products.dico.scanner.backend.helper.DistanceHelper;
import de.letsdev.products.dico.scanner.backend.helper.TimestampConverter;
import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.push.PushServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PushService pushService;

    @Autowired
    private Environment environment;

    private static final String DEFAULT_VALUE_MAX_DAYS = "14";
    private static final String DEFAULT_VALUE_BETWEEN_TIME = "30";
    private static final String DEFAULT_VALUE_MAX_DISTANCE = "200";
    private static final String PLACEHOLDER_TEXT = "{0}";

    Logger log = LoggerFactory.getLogger(LocationService.class);

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
    public void findNearlyLocations(Device device) {

        log.info("check nearly locations for device " + device.getId());
        List<Device> devicesToWarn = new ArrayList<>();
        String maxDaysConfig = environment.getProperty("coscan.search.maximum.days", DEFAULT_VALUE_MAX_DAYS);
        int maxDays = Integer.parseInt(maxDaysConfig);
        String searchBetweenTimeConfig = environment.getProperty("coscan.search.between.minutes",
                DEFAULT_VALUE_BETWEEN_TIME);
        int searchBetweenTime = Integer.parseInt(searchBetweenTimeConfig);
        String maxMetersConfig = environment.getProperty("coscan.search.area.alert.max.distance.meters",
                DEFAULT_VALUE_MAX_DISTANCE);
        int maxMeters = Integer.parseInt(maxMetersConfig);

        String title = environment.getProperty("push.message.test.title", "Positiver Covid-19 Test in der Nähe");
        String message = environment.getProperty("push.message.test.message",
                "In Ihrer Nähe gab es einen positiv getesteten Covid-19 Fall, lassen Sie sich testen.");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, -maxDays);
        Timestamp referenceTimestamp = new Timestamp(cal.getTime().getTime());

        Set<Location> locationsForPositiveDevice = device.getLocations();
        if(locationsForPositiveDevice == null) {
            log.info("device does not have assigned locations");
            return;
        }

        for (Location locationForPositiveDevice : locationsForPositiveDevice) {

            double latFrom = locationForPositiveDevice.getLat();
            double lonFrom = locationForPositiveDevice.getLon();

            if (locationForPositiveDevice.getTimestamp().after(referenceTimestamp)) {
                cal.setTime(locationForPositiveDevice.getTimestamp());
                cal.add(Calendar.MINUTE, -searchBetweenTime);
                Timestamp referenceTimestampBefore = new Timestamp(cal.getTime().getTime());
                cal.add(Calendar.MINUTE, 2 * searchBetweenTime);
                Timestamp referenceTimestampAfter = new Timestamp(cal.getTime().getTime());

                List<Location> locationsToCheck = locationRepository.findAllByTestResultPositiveAndTimestampBetweenAnd(
                        referenceTimestampBefore, referenceTimestampAfter, locationForPositiveDevice.getDevice().getId());
                log.info("found " + locationsToCheck.size() + " devices to check");

                for (Location location : locationsToCheck) {
                    if (!devicesToWarn.contains(location.getDevice()) && DistanceHelper.isDistanceSmallerThanReference(
                            latFrom, location.getLat(), lonFrom, location.getLon(), maxMeters)) {
                        log.info("device " + location.getDevice().getId() + " added. Location: " + location.getId());
                        devicesToWarn.add(location.getDevice());

                        message = message.replace(PLACEHOLDER_TEXT, location.getTimestamp().toString());
                        try {
                            pushService.sendPushToDevice(title, message, location.getDevice().getUuid(), "testDetected");
                        } catch (PushServiceException e) {
                            log.error("error while sending push message");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        log.info("found " + devicesToWarn.size() + " devices near to positive device");
    }
}

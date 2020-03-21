package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.db.TestState;
import de.letsdev.products.dico.scanner.backend.helper.TestStateConverter;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.service.LocationService;
import de.letsdev.products.dico.scanner.backend.service.TestStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class WSPositionController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private DeviceService deviceService;

    private static final String X_ATT_DEVICE_HEADER = "X-ATT-DeviceId";
    private Logger log = LoggerFactory.getLogger(PushService.class);

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> position(@RequestHeader(X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody Position position) {

        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if (device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        // business logic
        Location location = locationService.savePosition(device, position);

        //TODO search area
        if (location != null) {
            locationService.findNearlyLocations(location);
            log.info("location successfully saved for device: " + device.getId());
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/show/{deviceId}")
    @ResponseBody
    public List<Position> showLocation(@PathVariable("deviceId") String deviceId) {
        if (deviceId.equals("all")) {
            return locationService.findAll();
        }

        return locationService.findAllByDeviceUuid(deviceId);
    }

}

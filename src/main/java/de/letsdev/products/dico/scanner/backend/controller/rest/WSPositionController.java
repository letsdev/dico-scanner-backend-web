package de.letsdev.products.dico.scanner.backend.controller.rest;

import de.letsdev.products.dico.scanner.backend.ws.dto.Position;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Location;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/position")
public class WSPositionController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private DeviceService deviceService;

    private Logger log = LoggerFactory.getLogger(WSPositionController.class);

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> position(@RequestHeader(WSHelper.X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody
            Position position) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if (device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        // business logic
        Location location = locationService.savePosition(device, position);

        if (location != null) {
            log.info("location successfully saved for device: " + device.getId());
            return new ResponseEntity<>(true, HttpStatus.CREATED);
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

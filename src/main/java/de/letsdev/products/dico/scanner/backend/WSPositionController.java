package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.service.LocationService;
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


    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> position(@RequestHeader(WSHelper.X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody Position position) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if(device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        // business logic
        locationService.savePosition(device, position);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @GetMapping("/show/{deviceId}")
    @ResponseBody
    public List<Position> showLocation(@PathVariable("deviceId") String deviceId) {
        return locationService.findAllByDeviceUuid(deviceId);
    }

}

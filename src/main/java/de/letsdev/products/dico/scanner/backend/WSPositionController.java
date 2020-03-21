package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WSPositionController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(
            value = "/position/{device_id}",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> position(@PathVariable("device_id") String device_id, @RequestBody Position position) {
        Device device = deviceService.findByDeviceUuid(device_id);
        if(device == null) {
            device = deviceService.createDevice(device_id);
        }

        // business logic
        locationService.savePosition(device_id, position);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

}

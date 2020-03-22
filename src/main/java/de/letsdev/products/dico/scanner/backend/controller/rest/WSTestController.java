package de.letsdev.products.dico.scanner.backend.controller.rest;

import de.letsdev.products.dico.scanner.backend.ws.dto.Test;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.TestState;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.service.TestStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class WSTestController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TestStateService testStateService;

    private static final String X_ATT_DEVICE_HEADER = "X-ATT-DeviceId";

    @RequestMapping(path = "/create",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> createTest(@RequestHeader(X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody String timestamp) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if (device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        testStateService.createTest(timestamp, device);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/list/{deviceId}")
    @ResponseBody
    public List<Test> listTests(@PathVariable("deviceId") String deviceId) {
        return testStateService.findAllByDeviceUuid(deviceId);
    }

    @PostMapping("/sendResult")
    public ResponseEntity<Object> updateTest(@RequestHeader(X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody Test request) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if (device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        request.setId(0);

        List<Test> tests = testStateService.findAllByDeviceUuid(deviceIdHeader);
        for (Test test : tests) {
            if (test.getResult() == TestState.State.INITIALIZED) {
                request.setId(test.getId());
            }
        }

        testStateService.updateTest(request, device);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

}

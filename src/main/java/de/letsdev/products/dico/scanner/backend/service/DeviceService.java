package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    Logger log = LoggerFactory.getLogger(DeviceService.class);

    public Device findByDeviceUuid(String uuid) {
        return deviceRepository.findByUuid(uuid);
    }

    public Device createDevice(String uuid) {
        Device device = new Device();
        device.setUuid(uuid);
        deviceRepository.save(device);
        log.info("new device " + uuid + " created");
        return device;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}

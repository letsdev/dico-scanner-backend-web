package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public Device findByDeviceUuid(String uuid) {
        return deviceRepository.findByUuid(uuid);
    }

    public Device createDevice(String uuid) {
        Device device = new Device();
        device.setUuid(uuid);
        deviceRepository.save(device);
        return device;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}

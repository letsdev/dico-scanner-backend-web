package de.letsdev.products.dico.scanner.backend.scheduler;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.push.PushServiceException;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class SymptomsScheduler {

    @Autowired
    DeviceService deviceService;

    @Autowired
    PushService pushService;

    @Autowired
    Environment environment;

    Logger log = LoggerFactory.getLogger(SymptomsScheduler.class);

    @Scheduled(cron = "0 0 17 * * *", zone="Europe/Berlin")
    public void symptomsReminder() {
        log.info("daily reminder scheduler started");
        List<Device> devices = deviceService.getAllDevices();

        String title = environment.getProperty("push.message.reminder.title", "Tägliche Erinnerung");
        String message = environment.getProperty("push.message.reminder.message", "Bitte die Symptome für heute eingeben.");

        for(Device device : devices) {
            try {
                pushService.sendPushToDevice(title, message, device.getUuid(), "dailySymptomsReminder");
            } catch (PushServiceException e) {
                log.error("error while sending push message for device " + device.getUuid());
                e.printStackTrace();
            }
        }
    }
}

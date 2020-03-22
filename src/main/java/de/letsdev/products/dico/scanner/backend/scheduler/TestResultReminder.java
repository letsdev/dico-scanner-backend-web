package de.letsdev.products.dico.scanner.backend.scheduler;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.push.PushServiceException;
import de.letsdev.products.dico.scanner.backend.service.TestStateService;
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
public class TestResultReminder {

    @Autowired
    TestStateService testStateService;

    @Autowired
    PushService pushService;

    @Autowired
    Environment environment;

    Logger log = LoggerFactory.getLogger(TestResultReminder.class);

    @Scheduled(cron="0 30 17 * * *", zone="Europe/Berlin")
    public void doScheduledWork() {

        log.info("daily scheduler for test result reminder started");
        List<Device> devices = testStateService.findAllDevicesWithPendingTestAfterReferenceTime();
        String title = environment.getProperty("push.message.reminder.test.result.title", "Erinnerung für den Covid-19 Test");
        String message = environment.getProperty("push.message.reminder.test.result.message", "Bitte tragen Sie Ihren Covid-19 Testergebnis ein.");

        for(Device device : devices) {
            try {
                pushService.sendPushToDevice(title, message, device.getUuid(), "testDetected");
            } catch (PushServiceException e) {
                log.error("error while sending push message for device " + device.getUuid());
                e.printStackTrace();
            }
        }

    }
}

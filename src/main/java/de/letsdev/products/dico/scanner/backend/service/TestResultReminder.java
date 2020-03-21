package de.letsdev.products.dico.scanner.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@EnableScheduling
public class TestResultReminder {

    Logger log = LoggerFactory.getLogger(TestResultReminder.class);

    @Scheduled(cron="*/10 * * * * *", zone="Europe/Berlin")
    public void doScheduledWork() {

        log.info("asdsasadas");

    }
}

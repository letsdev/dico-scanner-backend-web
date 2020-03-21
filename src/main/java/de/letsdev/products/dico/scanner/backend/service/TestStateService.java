package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.Test;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.TestState;
import de.letsdev.products.dico.scanner.backend.db.TestStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestStateService {

    @Autowired
    private TestStateRepository testStateRepository;

    public void createTest(String timeString, Device device) {

        Instant instant = Instant.parse(timeString);
        Timestamp timestamp = Timestamp.from(instant);

        TestState testState = new TestState();
        testState.setState("initialized");
        testState.setTimestamp(timestamp);

        testStateRepository.save(testState);
    }

    public List<Test> findAllByDeviceUuid(String deviceUuid) {

        List<TestState> testStates = testStateRepository.findAllByDeviceUuid(deviceUuid);
        List<Test> tests = new ArrayList<>(testStates.size());

        for (TestState testState : testStates) {
            Test test = new Test(
                    testState.getTimestamp().toInstant().toString(),
                    testState.getState(),
                    testState.getResult()
            );
            tests.add(test);
        }

        return tests;
    }

}

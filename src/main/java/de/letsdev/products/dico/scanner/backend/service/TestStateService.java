package de.letsdev.products.dico.scanner.backend.service;

import de.letsdev.products.dico.scanner.backend.ws.dto.Test;
import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.TestState;
import de.letsdev.products.dico.scanner.backend.db.TestStateRepository;
import de.letsdev.products.dico.scanner.backend.helper.TimestampConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class TestStateService {

    @Autowired
    private TestStateRepository testStateRepository;

    private static final int REMEMBER_DAYS_PENDING_TEST = 2;

    public void createTest(String timeString, Device device) {

        Instant instant = Instant.parse(timeString);
        Timestamp timestamp = Timestamp.from(instant);

        TestState testState = new TestState();
        testState.setState(TestState.State.INITIALIZED);
        testState.setTimestamp(timestamp);

        testStateRepository.save(testState);
    }

    public List<Test> findAllByDeviceUuid(String deviceUuid) {

        List<TestState> testStates = testStateRepository.findAllByDeviceUuid(deviceUuid);
        List<Test> tests = new ArrayList<>(testStates.size());

        for (TestState testState : testStates) {
            Test test = new Test(testState.getId(), testState.getTimestamp().toInstant().toString(),
                    testState.getState());
            tests.add(test);
        }

        return tests;
    }

    public void updateTest(Test test, Device device) {

        TestState testState = new TestState();
        testState.setTimestamp(TimestampConverter.convertStringToTimestamp(test.getTimestamp()));
        testState.setDevice(device);
        testState.setState(test.getResult());
        testState.setId(test.getId());

        testStateRepository.save(testState);
    }

    public List<Device> findAllDevicesWithPendingTestAfterReferenceTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -REMEMBER_DAYS_PENDING_TEST);
        Timestamp referenceTimestamp = new Timestamp(calendar.getTimeInMillis());
        List<TestState> testStates = testStateRepository.findAllByStateAndTimestampBefore(TestState.State.INITIALIZED, referenceTimestamp);
        List<Device> devices = new ArrayList<>();
        for(TestState testState : testStates) {
            if(!devices.contains(testState.getDevice())) {
                devices.add(testState.getDevice());
            }
        }

        return devices;
    }

    public Set<String> findAllDevicesWithPositiveTests() {

        List<TestState> testStates = testStateRepository.findAllByState(TestState.State.IS_POSITIVE);
        HashSet<String> deviceSet = new HashSet<>();

        for (TestState testState : testStates) {
            deviceSet.add(testState.getDevice().getUuid());
        }

        return deviceSet;
    }

    public boolean hasPositiveTest(String deviceId) {

        List<TestState> testStates = testStateRepository.findAllByDeviceUuid(deviceId);
        for (TestState testState : testStates) {
            if (testState.getState() == TestState.State.IS_POSITIVE) {
                return true;
            }
        }

        return false;
    }
}

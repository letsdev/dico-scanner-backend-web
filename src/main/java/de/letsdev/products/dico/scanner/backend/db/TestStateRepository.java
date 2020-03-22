package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TestStateRepository extends JpaRepository<TestState, Long> {

    List<TestState> findAllByDeviceUuid(String uuid);

    List<TestState> findAllByStateAndTimestampBefore(TestState.State state, Timestamp timestamp);

    List<TestState> findAllByState(TestState.State state);
}

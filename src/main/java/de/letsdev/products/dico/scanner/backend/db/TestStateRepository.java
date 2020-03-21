package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestStateRepository extends JpaRepository<TestState, Long> {

    List<TestState> findAllByDeviceUuid(String uuid);
}

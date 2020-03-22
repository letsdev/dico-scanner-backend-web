package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByDeviceUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM location l LEFT JOIN device d on l.device_id = d.id LEFT JOIN test_state ts on ts.device_id = d.id WHERE l.timestamp BETWEEN :timestampBefore AND :timestampAfter AND d.id != :deviceId")
    List<Location> findAllByTestResultPositiveAndTimestampBetweenAnd(Timestamp timestampBefore, Timestamp timestampAfter, Long deviceId);
}

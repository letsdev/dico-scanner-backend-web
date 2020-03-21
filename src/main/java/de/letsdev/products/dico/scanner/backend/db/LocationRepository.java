package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByDeviceUuid(String uuid);

//    @Query(nativeQuery = true, value = "SELECT l.* FROM location l JOIN device d on l.device_id = d.id JOIN test_state ts on ts.device_id = d.id WHERE ts.state=1 AND l.timestamp BETWEEN :refDate1 AND :refDate2;")
    @Query(nativeQuery = true, value = "SELECT * FROM location l JOIN device d on l.device_id = d.id JOIN test_state ts on ts.device_id = d.id WHERE ts.state=1 AND l.timestamp BETWEEN :timestampBefore AND :timestampAfter AND d.id != :deviceId")
    List<Location> findAllByTestResultPositiveAndTimestampBetweenAnd(Timestamp timestampBefore, Timestamp timestampAfter, Long deviceId);
}

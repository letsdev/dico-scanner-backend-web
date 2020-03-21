package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByDeviceUuid(String uuid);

    List<Location> findAllByTimestampAfter(Timestamp timestamp);
}

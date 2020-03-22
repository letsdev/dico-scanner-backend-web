package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findById(long id);

    Device findByUuid(String uuid);
}

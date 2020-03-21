package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<TestState, Long> {

}

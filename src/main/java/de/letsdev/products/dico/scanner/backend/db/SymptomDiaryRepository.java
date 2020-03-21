package de.letsdev.products.dico.scanner.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomDiaryRepository extends JpaRepository<SymptomDiary, Long> {

    List<SymptomDiary> findAll();

    SymptomDiary save(SymptomDiary symptomDiary);
}

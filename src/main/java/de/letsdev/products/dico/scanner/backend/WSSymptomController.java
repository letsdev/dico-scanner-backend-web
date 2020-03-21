package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.SymptomRepository;
import de.letsdev.products.dico.scanner.backend.ws.dto.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/symptom")
public class WSSymptomController {

    @Autowired
    private SymptomRepository symptomRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public List<Symptom> findAll(@RequestHeader(value = WSHelper.X_ATT_DEVICE_HEADER, required = false) String deviceIdHeader) {

        List<de.letsdev.products.dico.scanner.backend.db.Symptom> dbResult = symptomRepository.findAll();

        List<Symptom> result = new ArrayList<>();

        for (de.letsdev.products.dico.scanner.backend.db.Symptom currentDbSymptom : dbResult) {

            Symptom dtoSymptom = new Symptom();
            dtoSymptom.setId(currentDbSymptom.getId());
            dtoSymptom.setNameDe(currentDbSymptom.getNameDe());
            result.add(dtoSymptom);
        }

        return result;
    }
}

package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Symptom;
import de.letsdev.products.dico.scanner.backend.db.SymptomDiaryRepository;
import de.letsdev.products.dico.scanner.backend.db.SymptomRepository;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.ws.dto.SymptomDiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/symptomDiary")
public class WSSymptomDiaryController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private SymptomDiaryRepository symptomDiaryRepository;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<Object> persistSymptomDiary(@RequestHeader(WSHelper.X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody SymptomDiary symptomDiary) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if(device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        de.letsdev.products.dico.scanner.backend.db.SymptomDiary dbSymptomDiary = new de.letsdev.products.dico.scanner.backend.db.SymptomDiary();
        dbSymptomDiary.setDevice(device);

        for (de.letsdev.products.dico.scanner.backend.ws.dto.Symptom currentDtoSymptom : symptomDiary.getSymptoms()) {

            Symptom tmpDbSymptom = this.symptomRepository.findById(currentDtoSymptom.getId());
            dbSymptomDiary.getSymptoms().add(tmpDbSymptom);
        }

        //dbSymptomDiary.setTimestamp(Timestampc);

        symptomDiaryRepository.save(dbSymptomDiary);

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

}

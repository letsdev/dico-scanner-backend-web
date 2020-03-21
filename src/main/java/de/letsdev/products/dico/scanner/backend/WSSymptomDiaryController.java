package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.Device;
import de.letsdev.products.dico.scanner.backend.db.Symptom;
import de.letsdev.products.dico.scanner.backend.db.SymptomDiaryRepository;
import de.letsdev.products.dico.scanner.backend.db.SymptomRepository;
import de.letsdev.products.dico.scanner.backend.helper.TimestampConverter;
import de.letsdev.products.dico.scanner.backend.service.DeviceService;
import de.letsdev.products.dico.scanner.backend.ws.dto.SymptomDiary;
import de.letsdev.products.dico.scanner.backend.ws.dto.SymptomDiaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<SymptomDiaryResponse> persistSymptomDiary(@RequestHeader(WSHelper.X_ATT_DEVICE_HEADER) String deviceIdHeader, @RequestBody SymptomDiary symptomDiary) {
        Device device = deviceService.findByDeviceUuid(deviceIdHeader);
        if(device == null) {
            device = deviceService.createDevice(deviceIdHeader);
        }

        SymptomDiaryResponse result = new SymptomDiaryResponse();

        de.letsdev.products.dico.scanner.backend.db.SymptomDiary dbSymptomDiary = new de.letsdev.products.dico.scanner.backend.db.SymptomDiary();
        dbSymptomDiary.setDevice(device);

        List<Integer> symptomIds = new ArrayList<>();

        for (de.letsdev.products.dico.scanner.backend.ws.dto.Symptom currentDtoSymptom : symptomDiary.getSymptoms()) {

            Symptom tmpDbSymptom = this.symptomRepository.findById(currentDtoSymptom.getId());
            dbSymptomDiary.getSymptoms().add(tmpDbSymptom);

            symptomIds.add(tmpDbSymptom.getId());
        }

        boolean hasFever = false;
        if(symptomIds.contains(3) || symptomIds.contains(4) || symptomIds.contains(5) || symptomIds.contains(6) || symptomIds.contains(7)){
            hasFever = true;
        }

        boolean infected = hasFever;
        if(infected && symptomIds.contains(2) && symptomIds.contains(12)){
            infected = true;
        }
        else {
            infected = false;
        }

        result.setMaybeInfected(infected);

        if(result.isMaybeInfected()){
            result.setMessage("Möglicherweise sind Sie aufgrund Ihrer Symptome an Corona erkrankt. Bitte lassen Sie sich testen!");
        }
        else {
            result.setMessage("Aufgrund Ihrer Symptome sind Sie nicht an Corona erkrankt. Bleiben Sie dennoch zuhause!");
        }

        dbSymptomDiary.setTimestamp(TimestampConverter.convertStringToTimestamp(symptomDiary.getTimestamp()));

        symptomDiaryRepository.save(dbSymptomDiary);

        return new ResponseEntity<SymptomDiaryResponse>(result, HttpStatus.CREATED);
    }

}

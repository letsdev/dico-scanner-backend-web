package de.letsdev.products.dico.scanner.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSPositionController {

    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public ResponseEntity<Object> position(@RequestBody Position position) {
        // business logic
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

}

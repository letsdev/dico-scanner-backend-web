package de.letsdev.products.dico.scanner.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WSPositionController {

    @RequestMapping(
            value = "/position/{device_id}",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity<APIResponse> position(@PathVariable("device_id") String device_id, @RequestBody Position position) {
        // business logic
        return new ResponseEntity<APIResponse>(new APIResponse("success", false), HttpStatus.ACCEPTED);
    }

}

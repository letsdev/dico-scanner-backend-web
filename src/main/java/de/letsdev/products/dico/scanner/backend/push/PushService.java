package de.letsdev.products.dico.scanner.backend.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class PushService {

    private static String hostUrl = "https://letspush.com";
    private static String endpoint = "/rest/notification";
    private static String restApiKey = "011c7b977916e2b7e77d35dce8518f6e6f6c413470c7d6c7b94467ff7457635f"; /* api key for covid */
    private static String bundleIdentifier = "de.letsdev.DiCoScanner"; /* api key for covid */
    //private static String restApiKey = "c149c1a0913e3f24a365fcdba6e38202474ebe8d7f67f0641b842e6658e6f83c"; /* Team key */

    Logger log = LoggerFactory.getLogger(PushService.class);

    public void sendPushToDevice(String title, String message, String deviceId) throws PushServiceException {
        this.sendPushToDevice(title, message, deviceId, null);
    }

    public void sendPushToDevice(String title, String message, String deviceId, String action) throws PushServiceException {

        String url = hostUrl + endpoint;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", restApiKey);
        headers.add("From", bundleIdentifier);

        RestTemplate restTemplate = new RestTemplate();

        PushRequestDto pushRequestDto = new PushRequestDto();
        pushRequestDto.setDeviceQuery("deviceId = [" + deviceId + "]");
        pushRequestDto.setTitle(title);
        pushRequestDto.setMessage(message);
        pushRequestDto.setId(UUID.randomUUID().toString());

        if(action != null){
            pushRequestDto.setPushData("{ \"action\": \"" + action + "\"}");
        }

        HttpEntity<PushRequestDto> request = new HttpEntity<>(pushRequestDto, headers);

        try {
            String responseString = restTemplate.postForObject(url, request, String.class);

            log.info("Push message created successfully: " + responseString);
        }
        catch (Exception e) {
            log.error("Push message NOT created, error: " + e);
            throw new PushServiceException(e);
        }
    }
}

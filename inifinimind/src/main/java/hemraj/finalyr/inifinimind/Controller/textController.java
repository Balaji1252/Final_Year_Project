package hemraj.finalyr.inifinimind.Controller;

import hemraj.finalyr.inifinimind.Model.Input;
import hemraj.finalyr.inifinimind.Service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@RequestMapping("/infai")
@RestController
public class textController {
    @Autowired
    registerService service;

    static final String uri="http://localhost:8000/classify";

    @PostMapping("/InputApi")
    public ResponseEntity<String> sendData(@RequestBody Input input) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();

        try {
            Input newInput = restTemplate.postForObject(uri, input, Input.class);
            service.saveInputData(input);
            return ResponseEntity.ok("request success"+newInput);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during rest call");
        }
    }


}

package CompressionMicroservice.controller;


import CompressionMicroservice.controller.response.FrequencyResponse;
import CompressionMicroservice.service.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrequencyController {

    @Autowired
    private FrequencyService frequencyService;

    @RequestMapping(value = "/frequency", method = RequestMethod.GET)
    public ResponseEntity<FrequencyResponse> frequency(@RequestParam(value = "text") String text) {
        FrequencyResponse response = new FrequencyResponse();
        response.setMapping(frequencyService.calculateFrequencies(text));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

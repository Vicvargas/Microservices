package CompressionMicroservice.controller;


import CompressionMicroservice.controller.response.EncodeResponse;
import CompressionMicroservice.service.EncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncoderController {

    @Autowired
    private EncodeService encodeService;

    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    public ResponseEntity<EncodeResponse> encode(@RequestParam(value = "text") String text) {
        EncodeResponse response = encodeService.encode(text);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

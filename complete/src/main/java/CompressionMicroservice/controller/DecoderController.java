package CompressionMicroservice.controller;

import CompressionMicroservice.controller.request.DecodeRequest;
import CompressionMicroservice.controller.response.DecodeResponse;
import CompressionMicroservice.exception.InvalidDecodeRequest;
import CompressionMicroservice.service.DecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecoderController {
    @Autowired
    private DecodeService decodeService;

    /**
     * POST decode
     * @param request
     * @return
     */
    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public ResponseEntity<DecodeResponse> encode(@RequestBody DecodeRequest request) {
        DecodeResponse response;
        try {
            response = decodeService.decode(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvalidDecodeRequest invalidDecodeRequest) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

package CompressionMicroservice.controller;


import CompressionMicroservice.controller.response.EncodeResponse;
import CompressionMicroservice.service.EncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})

public class EncoderController {

    @Autowired
    private EncodeService encodeService;

    @RequestMapping(value = "/encode", method = {RequestMethod.POST})
    public ResponseEntity<EncodeResponse> encode(@RequestParam("file") MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        System.out.printf("File name = %s, size = %s\n", file.getOriginalFilename(),file.getSize());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/encode", method = {RequestMethod.GET})
    public ResponseEntity<EncodeResponse> encodeGet(@RequestParam(value = "text") String text){
        List<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/josecespedesant/Microservices/CompressionMicroservice/Microservices/gs-spring-boot-docker/complete/"+text));
            String line;
            while((line = reader.readLine()) != null){
                records.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalstring = "";

        for(int i=0; i<records.size(); i++){
            finalstring += records.get(i);
            finalstring += "æ";
        }

        EncodeResponse response = encodeService.encode(finalstring);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
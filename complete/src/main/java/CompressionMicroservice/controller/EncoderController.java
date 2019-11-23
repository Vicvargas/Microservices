package CompressionMicroservice.controller;


import CompressionMicroservice.controller.response.EncodeResponse;
import CompressionMicroservice.linkedlist.SimpleLinkedList;
import CompressionMicroservice.service.EncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encargado de recibir las señales de codificación
 */

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})

public class EncoderController {

    @Autowired
    private EncodeService encodeService;

    /**
     * POST encode
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/encode", method = {RequestMethod.POST})
    public ResponseEntity<EncodeResponse> encode(@RequestParam("file") MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        System.out.printf("File name = %s, size = %s\n", file.getOriginalFilename(),file.getSize());

        //List<String> records = new ArrayList<String>();
        SimpleLinkedList<String> records = new SimpleLinkedList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(convFile));
            String line;
            while((line = reader.readLine()) != null){
                records.addLast(line);
                //records.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalstring = "";

        for(int i=0; i<records.getLength(); i++){
            finalstring += records.getFirst().getData();
            records.deleteFront();
            finalstring += "æ";
        }


        EncodeResponse response = encodeService.encode(finalstring);
        convFile.delete();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
package CompressionMicroservice.service;

import CompressionMicroservice.controller.response.EncodeResponse;
import CompressionMicroservice.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EncodeService {

    private static final Logger logger = LoggerFactory.getLogger(EncodeService.class);

    @Autowired
    private FrequencyService frequencyService;

    @Autowired
    private NodeService nodeService;

    public EncodeResponse encode(String text){
        Map<String, Integer> statisticsMap = frequencyService.calculateFrequencies(text);
        logger.info("statistics calculated.");
        Node root = nodeService.buildTreeWithStatistics(statisticsMap);
        logger.info("huffman tree created with statistics.");
        Map<String, String> mapping = nodeService.findMapping(root);
        String encodedText = nodeService.encodeDecodedStr(mapping, text);
        logger.info("text encoded.");

        EncodeResponse response = new EncodeResponse();
        response.setMapping(mapping);
        response.setEncodedText(encodedText);

        return response;



    }



}

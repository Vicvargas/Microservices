package CompressionMicroservice.service;

import CompressionMicroservice.controller.request.DecodeRequest;
import CompressionMicroservice.controller.response.DecodeResponse;
import CompressionMicroservice.exception.InvalidDecodeRequest;
import CompressionMicroservice.model.Node;
import CompressionMicroservice.repository.DecoderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DecodeService {

    private static final Logger logger = LoggerFactory.getLogger(DecodeService.class);

    @Autowired
    private NodeService nodeService;

    @Autowired
    private DecoderRepository decoderRepository;

    public DecodeResponse decode(DecodeRequest request) throws InvalidDecodeRequest {

        if(request.getEncodedText() == null || "".equals(request.getEncodedText())) {
            throw new InvalidDecodeRequest();
        }

        if(request.getMapping() == null || request.getMapping().isEmpty()) {
            throw new InvalidDecodeRequest();
        }

        DecodeResponse response = new DecodeResponse();

        Map<Integer, Node> decoderCash = decoderRepository.getDecoderCash();
        logger.info("cache retrieved, size: {}", decoderCash.size());

        Integer requestMappingHash = request.getMapping().hashCode();
        logger.info("requestMappingHash: {}", requestMappingHash);

        Node root = decoderCash.get(requestMappingHash);
        if(root == null) {
            logger.info("building new tree.");
            root = nodeService.buildTreeWithMapping(request.getMapping());
            decoderCash.put(requestMappingHash, root);
            logger.info("new tree stored in cache.");
        }

        response.setText(nodeService.decodeEncodedStr(root, request.getEncodedText()));
        return response;
    }



}

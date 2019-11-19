package CompressionMicroservice.repository;


import CompressionMicroservice.model.Node;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DecoderRepository {
    private Map<Integer, Node> decoderCash = new HashMap<>();

    public Map<Integer, Node> getDecoderCash() {
        return decoderCash;
    }
}

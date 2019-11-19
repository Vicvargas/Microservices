package CompressionMicroservice.controller.response;

import java.util.Map;

public class FrequencyResponse {
    private Map<String, Integer> mapping;

    public Map<String, Integer> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, Integer> mapping) {
        this.mapping = mapping;
    }
}

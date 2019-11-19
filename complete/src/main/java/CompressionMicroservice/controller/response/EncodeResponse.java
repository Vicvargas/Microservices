package CompressionMicroservice.controller.response;

import java.util.Map;

public class EncodeResponse {
    private String encodedText;
    private Map<String, String> mapping;

    public String getEncodedText() {
        return encodedText;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}

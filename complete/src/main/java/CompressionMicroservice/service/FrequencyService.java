package CompressionMicroservice.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class FrequencyService {
    public Map<String, Integer> calculateFrequencies(String text) {
        return calculateFrequencies(text.split(""));
    }

    private Map<String, Integer> calculateFrequencies(String[] strArray) {
        Map<String, Integer> statisticsMap = new HashMap<>();

        Arrays.stream(strArray).forEach(s -> {
            if(statisticsMap.containsKey(s)) {
                Integer repetition = statisticsMap.get(s);
                statisticsMap.put(s, ++repetition);
            } else {
                statisticsMap.put(s, 1);
            }
        });

        return statisticsMap;
    }
}

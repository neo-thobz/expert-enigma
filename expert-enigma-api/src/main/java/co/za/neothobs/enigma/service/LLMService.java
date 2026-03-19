package co.za.neothobs.enigma.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public LLMService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String generate(String prompt) {
        Map<String,Object> request = new HashMap<>();
        request.put("model", "mistral");
        request.put("prompt", prompt);
        request.put("stream", false);

        ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/api/generate", request, Map.class);
        return response.getBody().get("response").toString();
    }
}

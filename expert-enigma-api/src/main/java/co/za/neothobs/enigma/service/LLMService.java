package co.za.neothobs.enigma.service;

import java.util.Map;

import co.za.neothobs.enigma.model.OllamaResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public LLMService(@Value("${ollama.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String generate(String prompt) {
        var request = Map.of(
                "model", "mistral",
                "prompt", prompt,
                "stream", false
        );

        // Using the record for type-safe mapping
        ResponseEntity<OllamaResponse> response = restTemplate.postForEntity(
                baseUrl + "/api/generate",
                request,
                OllamaResponse.class
        );

        return response.getBody() != null ? response.getBody().response() : "";
    }

}

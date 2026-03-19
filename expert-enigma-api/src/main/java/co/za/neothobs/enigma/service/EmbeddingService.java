package co.za.neothobs.enigma.service;

import java.util.Map;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EmbeddingService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String ollamaBaseUrl = System.getenv("OLLAMA_BASE_URL"); // http://ollama:11434

    public float[] embedText(String text) {
        Map<String,Object> request = Map.of(
                "model", "nomic-embed-text",
                "prompt", text
        );

        Map<String,Object> response = restTemplate.postForObject(
                ollamaBaseUrl + "/api/embeddings",
                request,
                Map.class
        );

        // Ollama returns embeddings as List<Double>, convert to float[]
        var list = (List<Double>) response.get("embedding");
        float[] arr = new float[list.size()];
        for(int i = 0; i < list.size(); i++) arr[i] = list.get(i).floatValue();
        return arr;
    }
}

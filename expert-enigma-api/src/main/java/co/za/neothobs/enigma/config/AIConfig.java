package co.za.neothobs.enigma.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import co.za.neothobs.enigma.service.LLMService;

@Configuration
public class AIConfig {

    @Value("${ollama.base-url}")
    private String ollamaBaseUrl;

    @Bean
    public LLMService llmService() {
        return new LLMService(ollamaBaseUrl);
    }
}

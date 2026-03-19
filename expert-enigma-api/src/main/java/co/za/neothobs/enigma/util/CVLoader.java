package co.za.neothobs.enigma.util;

import co.za.neothobs.enigma.model.DocumentChunk;
import co.za.neothobs.enigma.repository.VectorRepository;
import co.za.neothobs.enigma.service.EmbeddingService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CVLoader implements CommandLineRunner {
    private final VectorRepository vectorRepository;
    private final EmbeddingService embeddingService;

    @Override
    public void run(String... args) throws Exception {
        // Example chunks
        String[] chunks = {
                "Neo is a Senior Java Developer with 5+ years of experience in Spring Boot and microservices.",
                "Worked at XYZ Corp building scalable banking APIs using event-driven architecture.",
                "Led a team of 4 engineers, mentoring junior developers and performing code reviews."
        };

        for(String chunk : chunks) {
            float[] embedding = embeddingService.embedText(chunk);
            DocumentChunk doc = DocumentChunk.builder()
                    .content(chunk)
                    .embedding(embedding)
                    .build();
            vectorRepository.save(doc);
        }

        System.out.println("CV chunks loaded with embeddings!");
    }
}

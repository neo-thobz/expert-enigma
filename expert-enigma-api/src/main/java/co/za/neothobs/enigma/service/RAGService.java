package co.za.neothobs.enigma.service;

import java.util.List;
import java.util.stream.Collectors;

import co.za.neothobs.enigma.model.DocumentChunk;
import co.za.neothobs.enigma.repository.VectorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RAGService {

    private final LLMService llmService;
    private final EmbeddingService embeddingService;
    private final VectorRepository vectorRepository;

    private final int TOP_K = 3;

    public String ask(String question) {
        // 1️⃣ Embed the question
        float[] questionEmbedding = embeddingService.embedText(question);

        // 2️⃣ Retrieve top-K similar CV chunks
        List<DocumentChunk> topChunks = vectorRepository.findTopKSimilar(questionEmbedding, TOP_K);

        // 3️⃣ Build context string
        String context = topChunks.stream()
                .map(DocumentChunk::getContent)
                .collect(Collectors.joining("\n---\n"));

        // 4️⃣ Build strict prompt
        String prompt = "You are an assistant that answers questions ONLY from Neo's CV.\n" +
                "If the answer is not present in the CV, reply: 'That information is not in the CV.'\n\n" +
                "Context:\n" + context + "\n\nQuestion: " + question;

        // 5️⃣ Generate answer
        return llmService.generate(prompt);
    }

}

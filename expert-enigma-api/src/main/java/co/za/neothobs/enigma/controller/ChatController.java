package co.za.neothobs.enigma.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import co.za.neothobs.enigma.model.ChatRequest;
import co.za.neothobs.enigma.model.ChatResponse;
import co.za.neothobs.enigma.service.RAGService;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final RAGService ragService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = ragService.ask(request.message());
        return new ChatResponse(answer);
    }
}

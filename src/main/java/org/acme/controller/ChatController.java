package org.acme.controller;

import org.acme.domain.chat.ChatAssistant;
import org.acme.domain.chat.ChatAssistantWithContext;
import org.acme.domain.chat.ChatComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatAssistant chatAssistant;
    private final ChatAssistantWithContext chatAssistantWithContext;
    private final ChatComponent chatComponent;

    @Autowired
    public ChatController(
            ChatAssistant chatAssistant,
            ChatAssistantWithContext chatAssistantWithContext,
            ChatComponent chatComponent
    ) {
        this.chatAssistant = chatAssistant;
        this.chatAssistantWithContext = chatAssistantWithContext;
        this.chatComponent = chatComponent;
    }

    @GetMapping("/single-question")
    public String chat(@RequestParam(name = "question") String question) {
        return chatAssistant.chat(question);
    }

    @GetMapping("/with-memory")
    public String chatWithMemory(@RequestParam(name = "question") String question) {
        return chatComponent.chat(question);
    }

    @GetMapping("/with-conversational-chain")
    public String chatWithConversationalChain(@RequestParam(name = "question") String question) {
        return chatComponent.chat(question);
    }

    @PostMapping("/make-an-essay")
    public String makeAnEssay(@RequestParam(name = "topic") String topic,
                              @RequestParam(name = "lines") int lines) {
        return chatAssistantWithContext.writeAnEssay(topic, lines);
    }
}

package org.acme.controller;

import org.acme.domain.chat.ChatAssistant;
import org.acme.domain.chat.ChatComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatAssistant chatAssistant;
    private final ChatComponent chatComponent;

    @Autowired
    public ChatController(
            ChatAssistant chatAssistant,
            ChatComponent chatComponent
    ) {
        this.chatAssistant = chatAssistant;
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
}

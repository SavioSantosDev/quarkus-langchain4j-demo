package org.acme.controller;

import org.acme.domain.chat.ChatAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatAssistant chatAssistant;

    @Autowired
    public ChatController(
            ChatAssistant chatAssistant
    ) {
        this.chatAssistant = chatAssistant;
    }


    @GetMapping
    public String hello(@RequestParam(name = "question") String question) {
        return chatAssistant.askAQuestion(question);
    }
}

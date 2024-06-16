package org.acme.controller;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import org.acme.domain.chat.ChatAssistanteForCustomerSupport;

@WebSocket(path = "/chat")
public class ChatSocket {

    private final ChatAssistanteForCustomerSupport assistant;

    public ChatSocket(ChatAssistanteForCustomerSupport assistant) {
        this.assistant = assistant;
    }

    @OnOpen
    public String onOpen() {
        return "Hello from Miles of Smiles, how can we help you?";
    }

    @OnTextMessage
    public String onMessage(String userMessage){
        return assistant.chat(userMessage);
    }
}
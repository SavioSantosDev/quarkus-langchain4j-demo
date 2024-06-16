package org.acme.domain.chat;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface ChatAssistant {
    String askAQuestion(String question);
}

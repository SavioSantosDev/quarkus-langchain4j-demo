package org.acme.domain.chat;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = ChatEmailService.class)
public interface ChatAssistantWithContext {
    @SystemMessage("Você é um profissional na área de linguagens")
    @UserMessage("Escreva uma redação sobre o tema {topic}. A redação deve ter no mínimo {minLines} linhas. Envie a redação por email.")
    String writeAnEssay(String topic, int minLines);
}

package org.acme.domain.chat;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.output.Response;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.beans.factory.annotation.Autowired;

import static dev.langchain4j.data.message.UserMessage.userMessage;

@ApplicationScoped
public class ChatComponent {
    private final Tokenizer tokenizer = new OpenAiTokenizer(OpenAiChatModelName.GPT_3_5_TURBO);
    private final ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(1000, tokenizer);

    private final ChatLanguageModel model;
    private final ConversationalChain chain;

    @Autowired
    public ChatComponent(
            ChatLanguageModel model
    ) {
        this.model = model;
        this.chain = ConversationalChain.builder()
                .chatLanguageModel(model)
                .build();
    }

    public String chat(String question) {
        UserMessage message = userMessage(question);
        chatMemory.add(message);

        final Response<AiMessage> response = model.generate(chatMemory.messages());
        chatMemory.add(response.content());

        System.out.println("[Quantidade de tokens atual]: " + tokenizer.estimateTokenCountInMessages(chatMemory.messages()) + System.lineSeparator());

        return response.content().text();
    }

    public String chat2(String question) {
        return chain.execute(question);
    }
}

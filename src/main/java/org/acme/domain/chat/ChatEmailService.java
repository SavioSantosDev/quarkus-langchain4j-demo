package org.acme.domain.chat;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationScoped
public class ChatEmailService {
    @Autowired
    Mailer mailer;

    @Autowired
    ChatComponent chatComponent;

    @Tool("Envie o conteúdo por email")
    public void sendContentByEmail(String content) {
        Log.info("Enviando por email: " + content);

        String to = "foo_bar@email.com";
        String from = "quarkus_demo@email.com";
        String subject = "Redação";

        mailer.send(Mail.withText(to, subject, content).setFrom(from));
    }
}

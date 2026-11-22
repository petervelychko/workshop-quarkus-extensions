package dev.demo.quarkus.messagekit.runtime;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import dev.demo.messagekit.MessageCatalog;
import dev.demo.messagekit.MessageFormatter;

/** Lab 2: CDI producer supplied by the extension runtime module. */
@ApplicationScoped
public class MessageFormatterProducer {

    @Produces
    @Singleton
    public MessageFormatter formatter() {
        MessageCatalog catalog = new MessageCatalog(Map.of(
                "en", "Hello", "es", "Hola", "nl", "Hallo"));
        return new MessageFormatter(catalog, "en", "", false);
    }
}

package dev.demo.app;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import dev.demo.messagekit.MessageCatalog;
import dev.demo.messagekit.MessageFormatter;

/** Manual application-owned wiring. Lab 2 moves this into the extension. */
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

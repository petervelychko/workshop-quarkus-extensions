package dev.demo.quarkus.messagekit.runtime;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import dev.demo.messagekit.MessageCatalog;
import dev.demo.messagekit.MessageFormatter;

/** The formatter now receives public runtime configuration. */
@ApplicationScoped
public class MessageFormatterProducer {

    @Produces
    @Singleton
    public MessageFormatter formatter(MessageKitConfig config) {
        MessageCatalog catalog = new MessageCatalog(Map.of(
                "en", "Hello", "es", "Hola", "nl", "Hallo"));
        return new MessageFormatter(catalog, config.language(), config.prefix().orElse(""), config.uppercase());
    }
}

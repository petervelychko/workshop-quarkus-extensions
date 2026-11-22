package dev.demo.messagekit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageFormatterTest {

    private final MessageCatalog catalog = new MessageCatalog(Map.of("en", "Hello", "es", "Hola", "nl", "Hallo"));

    @Test
    @DisplayName("Formats a message using the English greeting")
    void formatsEnglishMessage() {
        assertEquals("Hello Ada", new MessageFormatter(catalog, "en", "", false).format("Ada"));
    }

    @Test
    @DisplayName("Applies the prefix and uppercases a Spanish greeting")
    void formatsSpanishAndUppercase() {
        assertEquals("WELCOME HOLA ADA", new MessageFormatter(catalog, "es", "Welcome", true).format("Ada"));
    }

    @Test
    @DisplayName("Formats a message using the Dutch greeting")
    void formatsDutchMessage() {
        assertEquals("Hallo Noor", new MessageFormatter(catalog, "nl", "", false).format("Noor"));
    }

    @Test
    @DisplayName("Applies a Dutch prefix and uppercases the greeting")
    void formatsDutchWithPrefixAndUppercase() {
        assertEquals("WELKOM HALLO NOOR", new MessageFormatter(catalog, "nl", "Welkom", true).format("Noor"));
    }

    @Test
    @DisplayName("Falls back to English when the language is unknown")
    void fallsBackToEnglishForUnknownLanguage() {
        assertEquals("Hello Ada", new MessageFormatter(catalog, "unknown", "", false).format("Ada"));
    }
}

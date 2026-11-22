package dev.demo.quarkus.messagekit.deployment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.inject.Inject;

import dev.demo.messagekit.MessageFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusExtensionTest;

public class MessageKitDutchExtensionTest {

    @RegisterExtension
    static final QuarkusExtensionTest app = new QuarkusExtensionTest()
            .withEmptyApplication()
            .overrideRuntimeConfigKey("quarkus.messagekit.language", "nl")
            .overrideRuntimeConfigKey("quarkus.messagekit.prefix", "Welkom")
            .overrideRuntimeConfigKey("quarkus.messagekit.uppercase", "true");

    @Inject
    MessageFormatter formatter;

    @Test
    @DisplayName("Applies Dutch language, prefix and uppercase formatting")
    void dutchRuntimeConfigurationChangesTheMessage() {
        assertEquals("WELKOM HALLO NOOR", formatter.format("Noor"));
    }
}

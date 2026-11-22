package dev.demo.quarkus.messagekit.runtime;

import java.util.Map;

import dev.demo.messagekit.MessageCatalog;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

/**
 * A recorder arranges runtime initialization using values chosen during
 * augmentation. Build steps invoke its recorder proxy during augmentation.
 */
@Recorder
public class MessageKitRecorder {

    public RuntimeValue<MessageCatalog> createCatalog(String english, String spanish, String dutch) {
        return new RuntimeValue<>(new MessageCatalog(Map.of("en", english, "es", spanish, "nl", dutch)));
    }
}

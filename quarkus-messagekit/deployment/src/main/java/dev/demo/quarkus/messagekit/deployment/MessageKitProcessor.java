package dev.demo.quarkus.messagekit.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

/** Quarkus augmentation processor for the MessageKit extension. */
public class MessageKitProcessor {

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("messagekit");
    }
}

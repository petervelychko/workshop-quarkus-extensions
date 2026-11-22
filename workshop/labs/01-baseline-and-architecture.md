# Lab 1 - Baseline application and extension architecture

Start tag: `workshop-00`. Reference checkpoint: `workshop-01`.

## Goal

Understand the existing Java library, manual CDI integration and the
runtime/deployment split. Add a basic build step to the extension processor.

## Starting point

The project already has Maven modules for `messagekit-core`, `quarkus-messagekit`
(runtime and deployment) and `demo-app`. The application currently owns a
CDI producer. No Quarkus extension integration is registered yet.

## Tasks

1. Run `mvn clean install` from the repository root.
2. Inspect `MessageFormatter` and `MessageCatalog` in `messagekit-core`.
3. Find the manual `MessageFormatterProducer` in `demo-app` and the REST
   endpoint in `GreetingResource`.
4. Inspect `quarkus-messagekit/runtime/pom.xml` and the extension metadata.
   Explain why the application depends on the runtime artifact only.
5. Open `quarkus-messagekit/deployment/.../MessageKitProcessor.java`.
   Add an `@BuildStep` method that returns `new FeatureBuildItem("messagekit")`.

## Check

```bash
mvn clean install
cd demo-app && mvn quarkus:dev
# In another terminal: curl http://localhost:8080/hello/Ada
```

The endpoint should still return `Hello Ada`. No formatting behavior is changed
by the feature build step. The key new fact is that augmentation discovers
extension deployment logic.

## Hints

- Use `io.quarkus.deployment.annotations.BuildStep`.
- Use `io.quarkus.deployment.builditem.FeatureBuildItem`.
- Build items are results consumed by Quarkus or other build steps.

## Discussion

What does the extension deployment artifact do that the runtime artifact
should not do? Why does this small library not need an extension yet?

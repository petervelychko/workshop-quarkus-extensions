# Lab 2 - Build steps, build items and CDI registration

Reference checkpoint: `workshop-02`.

## Goal

Move application-owned CDI wiring into the reusable extension.

## Tasks

1. Move `MessageFormatterProducer` from the application into the extension's
   runtime module. Change its package to `dev.demo.quarkus.messagekit.runtime`.
2. Keep the producer as an `@ApplicationScoped` bean with a `@Produces`
   method returning `@Singleton MessageFormatter`.
3. For now, construct `MessageCatalog` inside that method with greetings
   `en -> Hello`, `es -> Hola` and `nl -> Hallo`.
4. In `MessageKitProcessor`, add a second `@BuildStep` returning an
   `AdditionalBeanBuildItem`. Register `MessageFormatterProducer.class`.
5. Remove the application-owned producer. The REST resource must continue
   using `@Inject MessageFormatter` without changes.

## Check

Run `mvn clean install`, start `demo-app` with `mvn quarkus:dev`, then:

```bash
curl http://localhost:8080/hello/Ada
# Hello Ada
```

The endpoint should still work with no producer in the application module.

## Hints

`AdditionalBeanBuildItem.builder().addBeanClass(...).setUnremovable().build()`
is a useful pattern for this exercise. In production, avoid unremovable beans
unless there is a reason to retain them.

## Discussion

Why does a class inside an extension dependency not automatically become a
CDI bean in the consumer application?

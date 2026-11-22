# Lab 4 - Recorder and synthetic CDI bean

Reference checkpoint: `workshop-04`.

## Goal

Construct `MessageCatalog` through a recorder at runtime initialization and
register it as a synthetic CDI bean from the deployment module.

## Tasks

1. Create `MessageKitRecorder` in the runtime module with `@Recorder`.
2. Add `RuntimeValue<MessageCatalog> createCatalog(String english, String
   spanish, String dutch)`. The method should return a runtime value wrapping
   a new catalog containing `en`, `es` and `nl`.
3. Modify `MessageFormatterProducer` to receive an injected `MessageCatalog`
   parameter instead of constructing a catalog itself.
4. Add a build step in `MessageKitProcessor` with
   `@Record(ExecutionTime.RUNTIME_INIT)` and a `MessageKitRecorder` parameter.
5. Return `SyntheticBeanBuildItem.configure(MessageCatalog.class)` with
   `Singleton` scope, `.setRuntimeInit()` and
   `.runtimeValue(recorder.createCatalog("Hello", "Hola", "Hallo"))`.
6. Rebuild and verify the existing application behavior.

## Check

```bash
mvn clean install
cd demo-app && mvn quarkus:dev
# In another terminal: curl http://localhost:8080/hello/Ada
```

The endpoint still returns `Hello Ada`. The bean creation mechanism, not the
external behavior, is the significant change.

## Hints

- `MessageKitRecorder` belongs in `runtime`, not `deployment`.
- The processor and `SyntheticBeanBuildItem` belong in `deployment`.
- The build step executes during augmentation; a recorded operation executes
  in the designated initialization phase.
- `.setRuntimeInit()` must align with `@Record(RUNTIME_INIT)` here.

## Discussion

At what point does `MessageKitProcessor` execute? When is the actual catalog
created? Why is it incorrect to treat augmentation and runtime init as one
phase? Why can a synthetic bean be useful even if a producer could work?

## Optional challenge

Compare the implications of static and runtime init. Do not just switch the
annotation: first determine whether the initialization is safe in both modes.

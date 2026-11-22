# Student guide

Use this guide while working through the labs in `workshop/labs/`. The goal is
to turn the `messagekit-core` Java library into a reusable Quarkus extension
and verify that a consuming application can use it.

Start with the [project README](../README.md) for prerequisites and setup.
Follow the labs in order, starting from `workshop-00` on your own Git branch.

## Learning path

| Lab | What you will do | Completed checkpoint |
|-----|------------------|----------------------|
| 1 | Explore the baseline app and add a feature build step | `workshop-01` |
| 2 | Register the formatter producer from the extension | `workshop-02` |
| 3 | Expose runtime configuration for English, Spanish and Dutch | `workshop-03` |
| 4 | Create a catalog with a recorder and register a synthetic bean | `workshop-04` |
| 5 | Write tests for extension beans and configuration | `workshop-05` |

The `main` branch contains the complete reference solution.

## Check your work

Run the build from the repository root after each lab:

```bash
mvn clean install
```

To run the consuming application after a successful build:

```bash
cd demo-app
mvn quarkus:dev
```

In a separate terminal, request a greeting:

```bash
curl http://localhost:8080/hello/Ada
```

Unless you changed the application configuration, the expected result is
`Hello Ada`. Labs 3 and 5 describe how to verify Spanish and Dutch behavior.

## Use checkpoints safely

You can compare your implementation with the completed checkpoint without
changing your working files:

```bash
git diff workshop-03 workshop-04
```

To examine a specific file at a checkpoint:

```bash
git show workshop-04:quarkus-messagekit/deployment/src/main/java/dev/demo/quarkus/messagekit/deployment/MessageKitProcessor.java
```

If you want to restart from a checkpoint, save your changes before creating
a new branch:

```bash
git add .
git commit -m "Save my work"
git switch -c retry-lab-4 workshop-03
```

Avoid commands that discard uncommitted changes.

## Troubleshooting

- **Incorrect Java version:** Check `java -version`, `mvn -version` and
  `JAVA_HOME`. Use JDK 25 for this repository.
- **Missing runtime or deployment artifact:** Run `mvn clean install` from
  the repository root before launching the demo app separately.
- **Failed CDI injection after Lab 2:** Check the package of
  `MessageFormatterProducer` and its `AdditionalBeanBuildItem` registration.
- **Failure during augmentation after Lab 4:** Check that the recorder is in
  `runtime` and that `.setRuntimeInit()` matches `@Record(RUNTIME_INIT)`.
- **Unexpected greeting:** Check the configured language, optional prefix
  and uppercase property. The defaults use English without a prefix.
- **Conflicting extension tests:** Put tests using different runtime
  configuration overrides into separate test classes.

## Self-check

After completing the labs, you should be able to:

- Explain what belongs in the extension's runtime and deployment modules.
- Describe the difference between augmentation and runtime initialization.
- Inject `MessageFormatter` in a consuming app without an app-owned producer.
- Change the greeting using Spanish and Dutch runtime configuration.
- Explain how the recorder provides a synthetic `MessageCatalog` bean.
- Verify the extension using `QuarkusExtensionTest` and the demo app tests.

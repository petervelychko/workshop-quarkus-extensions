# Developing Quarkus Extensions

In this workshop you will turn a small Java library into a reusable
Quarkus extension. Follow the hands-on labs to explore build steps, build
items, CDI integration, runtime configuration, recorders, synthetic beans
and extension tests.

## Requirements

- JDK 25 (both `java -version` and `mvn -version` must show Java 25).
- Maven 3.9.16 or later.
- Git and a Java IDE.
- Internet access on the first build to download Maven dependencies.

This project uses Quarkus 3.40.1.2 and Java 25 settings.
The Maven parent artifact is `dev.demo:workshop-quarkus-extensions`.

## Quick start: complete example

The default `main` branch is the finished, working reference implementation.
To build all modules and run all tests:

```bash
mvn clean install
```

Then start the consuming application:

```bash
cd demo-app
mvn quarkus:dev
```

From another terminal:

```bash
curl http://localhost:8080/hello
# Hello Quarkus
curl http://localhost:8080/hello/Ada
# Hello Ada
```

The consuming app depends on the runtime extension artifact. Quarkus resolves
its corresponding deployment artifact using the generated extension descriptor.

## Quick start: hands-on labs

This repository includes Git tags for each lab checkpoint. If you are using
an archive, make sure `.git` was preserved during extraction. From the root:

```bash
git tag --list 'workshop-*'
git switch -c my-workshop-work workshop-00
mvn clean install
```

Keep working on your own branch as you complete the five labs in order:

1. [Baseline and extension structure](workshop/labs/01-baseline-and-architecture.md)
2. [Build steps, build items and CDI](workshop/labs/02-build-steps-and-cdi.md)
3. [Configuration](workshop/labs/03-configuration.md)
4. [Recorders and synthetic beans](workshop/labs/04-recorders-and-synthetic-beans.md)
5. [Extension tests](workshop/labs/05-extension-tests.md)

Each `workshop-NN` tag represents the *completed* state after that lab
(`workshop-00` is the working starting point).

**Keep your work safe:** The project has no script that discards changes.
Commit your progress before switching branches. If you get stuck, use a fresh
branch from a completed checkpoint, for example:

```bash
git add .
git commit -m "Save my progress"
git switch -c recovery-from-03 workshop-03
```

You can examine a solution without changing your worktree:

```bash
git diff workshop-02 workshop-03
```

Or use `git show workshop-04:path/to/file` to inspect a single file.
Do not run a destructive reset to move between lab stages.

## Repository modules

| Module                                    | Purpose                                          |
|-------------------------------------------|--------------------------------------------------|
| `messagekit-core/`                        | Plain Java formatting library                    |
| `quarkus-messagekit/runtime/`             | Configuration mapping, CDI producer and recorder |
| `quarkus-messagekit/deployment/`          | Build steps and synthetic bean registration      |
| `quarkus-messagekit/deployment/src/test/` | Extension tests                                  |
| `demo-app/`                               | REST application that consumes the extension     |
| `workshop/labs/`                          | Step-by-step participant exercises               |
| `workshop/STUDENT_GUIDE.md`               | Lab checkpoints, troubleshooting and self-checks |

## Runtime configuration (completed solution)

`MessageKitConfig` defines three properties:

```properties
quarkus.messagekit.language=nl
quarkus.messagekit.prefix=Welkom
quarkus.messagekit.uppercase=true
```

With these values, `/hello/Noor` returns `WELKOM HALLO NOOR`.
For Spanish, use:

```properties
quarkus.messagekit.language=es
quarkus.messagekit.prefix=Bienvenido
quarkus.messagekit.uppercase=true
```

With these values, `/hello/Lucia` returns `BIENVENIDO HOLA LUCIA`.
The default is English (`en`) with no prefix and no uppercase conversion.
The catalog falls back to English for unknown language codes.

## Why the extension has two modules

- `deployment` runs at augmentation time. `MessageKitProcessor` produces
  `FeatureBuildItem`, `AdditionalBeanBuildItem` and `SyntheticBeanBuildItem`.
- `runtime` supplies application code, `MessageKitConfig` and
  `MessageKitRecorder`. The recorder is invoked through a build-time proxy,
  while the recorded operation executes at the selected initialization phase.
- The runtime producer creates a configured `MessageFormatter` using the
  injected `MessageCatalog` synthetic bean.

The recorded `createCatalog()` call is marked `RUNTIME_INIT` and the synthetic
bean is also marked `.setRuntimeInit()` to keep those phases aligned. The
purpose is to demonstrate lifecycle and extension integration; no performance
benefit is claimed for this small formatting library.

## Tests

- `messagekit-core` has regular JUnit tests for formatting and fallback.
- `quarkus-messagekit/deployment` has `QuarkusExtensionTest` tests for
  injected services, Spanish/Dutch configuration and an empty prefix.
- `demo-app` has HTTP tests for the consuming application's REST endpoint.

Use `@DisplayName` for test descriptions. Test data and project text are
ASCII-only (including Spanish and Dutch strings).

## Official resources

- https://quarkus.io/guides/building-my-first-extension
- https://quarkus.io/guides/writing-extensions
- https://quarkus.io/guides/cdi-integration
- https://quarkus.io/guides/config-mappings

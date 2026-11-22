# Lab 3 - Public extension configuration

Reference checkpoint: `workshop-03`.

## Goal

Expose language, optional prefix and uppercase settings to the application.

## Tasks

1. Create `MessageKitConfig` in the extension runtime module.
2. Annotate it with `@ConfigRoot(phase = ConfigPhase.RUN_TIME)` and
   `@ConfigMapping(prefix = "quarkus.messagekit")`.
3. Define three members:
   - `String language()` defaulting to `en`;
   - `Optional<String> prefix()` with no default;
   - `boolean uppercase()` defaulting to `false`.
4. Inject/use `MessageKitConfig` in `MessageFormatterProducer` to construct
   the formatter. Use `config.prefix().orElse("")`.
5. Try both Spanish and Dutch values in
   `demo-app/src/main/resources/application.properties`.

## Check

For Spanish:

```properties
quarkus.messagekit.language=es
quarkus.messagekit.prefix=Bienvenido
quarkus.messagekit.uppercase=true
```

`curl http://localhost:8080/hello/Lucia` should return
`BIENVENIDO HOLA LUCIA`.

For Dutch:

```properties
quarkus.messagekit.language=nl
quarkus.messagekit.prefix=Welkom
quarkus.messagekit.uppercase=true
```

`curl http://localhost:8080/hello/Noor` should return `WELKOM HALLO NOOR`.
Restore default English properties before moving on.

## Hints

Use `io.smallrye.config.ConfigMapping` and `WithDefault`.
Do not use `@WithDefault("")` for a required String mapping: an empty string
may be treated as a missing value. An optional prefix is simpler here.

## Discussion

Why are configuration property names and defaults part of a public extension
contract? What difference does the configuration phase make?

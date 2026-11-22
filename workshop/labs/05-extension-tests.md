# Lab 5 - Test the extension as a consumer

Reference checkpoint: `workshop-05` (also the `main` branch).

## Goal

Verify the extension's CDI integration and runtime configuration using
`QuarkusExtensionTest` rather than testing only the plain library.

## Tasks

1. Add `MessageKitExtensionTest` under the deployment module test tree.
   Register `new QuarkusExtensionTest().withEmptyApplication()` as a JUnit
   `@RegisterExtension`. Inject a `MessageFormatter` and assert that
   `formatter.format("Quarkus")` returns `Hello Quarkus`.
2. Add a Spanish test overriding runtime config: language `es`, prefix
   `Bienvenido`, uppercase `true`. Verify `BIENVENIDO HOLA LUCIA` for `Lucia`.
3. Add a Dutch test overriding runtime config: language `nl`, prefix
   `Welkom`, uppercase `true`. Verify `WELKOM HALLO NOOR` for `Noor`.
4. Bonus: assert that an explicitly empty prefix is supported.
5. Keep a descriptive `@DisplayName` on each JUnit test method.

## Check

```bash
mvn clean install
```

This runs plain unit tests, deployment extension tests and the consuming
application REST tests.

## Hints

Use `overrideRuntimeConfigKey` to supply runtime configuration per test.
Keep tests in separate classes when each requires a different Quarkus runtime
configuration, so their test environments do not overlap.

## Discussion

What do `QuarkusExtensionTest` tests prove that plain JUnit tests on
`MessageFormatter` cannot? Why retain HTTP tests in the consuming app?

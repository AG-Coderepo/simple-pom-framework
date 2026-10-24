# Simple Page Object Model Framework

Stage 1: one Selenium Java/TestNG login test with hardcoded public demo credentials.

## Files

- `src/test/java/demo/pages/LoginPage.java`: locators and page actions.
- `src/test/java/demo/tests/LoginTest.java`: browser setup, scenario, assertions, cleanup.
- `pom.xml`: Maven dependencies and Surefire configuration.

## Run

Install Java 17+, Maven, and Google Chrome. From the repository folder:

```sh
mvn -Dtest=LoginTest test
```

Chrome runs headless by default. To watch locally in PowerShell:

```powershell
$env:HEADLESS = 'false'
mvn -Dtest=LoginTest test
```

Selenium Manager obtains the driver; first use requires network access. The test opens https://the-internet.herokuapp.com/login using the public demo credentials published there, then checks the success message and `/secure` URL. Test reports appear in `target/surefire-reports/`.

The page object receives the test's WebDriver through its constructor. TestNG's `@BeforeMethod` creates the browser; `@Test` runs the scenario; `@AfterMethod(alwaysRun = true)` closes it. Assertions stay in the test. Explicit waits handle page readiness.

No `testng.xml` is required at this stage: Surefire discovers `LoginTest` and invokes TestNG.

Execution status: source prepared; this new login test has not yet been compiled or browser-verified in the assistant environment. Earlier Docker title-test results do not validate this login test.

Next stage: move URL and demo credentials to a properties file. Real credentials must not be committed.

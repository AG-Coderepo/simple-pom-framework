# Simple Page Object Model Framework

Stage 1: a local Selenium Java/TestNG login test with hardcoded public demo credentials.

## Run in IntelliJ

Open this folder as a Maven project, use Java 17+, and install Google Chrome. Open `src/test/java/demo/tests/LoginTest.java`, then right-click the class and select Run LoginTest. Chrome opens visibly, logs into the practice website, verifies success, and closes. No environment variables are required.

Alternatively, with Maven installed, run `mvn -Dtest=LoginTest test`.

## Understand the two classes

- `LoginPage`: locators and reusable browser actions; receives the browser from the test.
- `LoginTest`: BeforeMethod opens Chrome, Test performs the scenario and assertions, AfterMethod closes Chrome even after failure.

Selenium Manager obtains ChromeDriver automatically; initial setup requires internet access. Reports appear in `target/surefire-reports/`.

Credentials are the public sample values published at https://the-internet.herokuapp.com/login. Next stage: read the URL and credentials from a properties file. Never commit real passwords.

Verification: the previous version compiled, but browser startup was blocked by the assistant environment's permissions. This simplified version has not yet been browser-verified.

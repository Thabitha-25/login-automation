# login-automation - Selenium + TestNG (Maven + Java 17)

This project automates login scenarios for https://the-internet.herokuapp.com/login using Selenium WebDriver, TestNG, Java 17, and Maven.

---

## Features

- Valid login test
- Invalid login test
- Empty fields test
- Page Object Model (POM)
- Data-driven testing using CSV
- Uses config.properties for configuration
- ChromeDriver included inside project (drivers/)

---

## Project Structure

```
login-automation/
│
├── drivers/
│     └── chromedriver.exe
│
├── src/
│   ├── main/java/com/login/base/
│   │        ├── BaseTest.java
│   │        └── ConfigReader.java
│   │
│   ├── main/resources/
│   │        └── config.properties
│   │
│   ├── test/java/com/login/tests/
│   │        ├── LoginPage.java
│   │        ├── SecureAreaPage.java
│   │        ├── LoginTest.java
│   │        └── DataDrivenLoginTest.java (optional)
│   │
│   └── test/resources/
│          └── testdata/loginData.csv (optional)
│
├── pom.xml
└── README.md
```

---

## Prerequisites

Install the following:

1. Java 17
2. Eclipse IDE
3. TestNG plugin - Help then Eclipse Marketplace then Search "TestNG" then Install
4. Maven (built-in to Eclipse)

---

## Configuration File

Location: src/main/resources/config.properties

Example content:

```properties
url=https://the-internet.herokuapp.com/login
browser=chrome
driverPath=drivers/chromedriver.exe
```

---

## How to Run the Tests

### Step 1: Import project as Maven project

Eclipse then File then Import then Maven then Existing Maven Projects then Select "login-automation"

### Step 2: Run test cases

**Run LoginTest.java**
- Right-click then Run As then TestNG Test

**Run DataDrivenLoginTest.java**
- Right-click then Run As then TestNG Test

No Test Suite XML is required.

---

## Test Cases Covered

### 1. Valid Login
- Username: tomsmith
- Password: SuperSecretPassword!
- Assert success message

### 2. Invalid Login
- Wrong username/password
- Assert: "Your username is invalid!"

### 3. Empty Fields
- No username and password
- Assert: required/error message

### 4. Data-Driven (Optional)
- CSV file: src/test/resources/testdata/loginData.csv

---

## ChromeDriver Setup

ChromeDriver available inside: login-automation/drivers/chromedriver.exe

Used in BaseTest:

```java
System.setProperty("webdriver.chrome.driver",
        System.getProperty("user.dir") + "/drivers/chromedriver.exe");
```

---

## Run with Maven (optional)

Run all TestNG tests:

```bash
mvn clean test
```

---

## Dependencies (pom.xml)

- Selenium Java
- TestNG
- WebDriverManager (optional)

---

## Project Highlights

- Maven-based project structure
- TestNG for test execution and assertions
- Page Object Model design pattern
- Configuration-driven using properties file
- Data-driven testing capability with CSV
- Embedded ChromeDriver for easy setup
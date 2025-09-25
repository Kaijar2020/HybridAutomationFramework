# HybridAutomationFramework

An automation test framework built in **Java**, using **Selenium WebDriver** and **TestNG**, leveraging the **Page Object Model (POM)** design pattern with **PageFactory**.

---

## 📖 Table of Contents

1. [Overview](#overview)  
2. [Features](#features)  
3. [Project Structure](#project-structure)  
4. [Prerequisites](#prerequisites)  
5. [Setup & Installation](#setup--installation)  
6. [Test Execution](#test-execution)  
7. [Reports & Artifacts](#reports--artifacts)  

---

## Overview

This framework provides a **hybrid automation solution** combining **data-driven** and **keyword-driven** approaches.  
It simplifies writing, organizing, and maintaining automated UI tests across different modules, browsers, and data sets.

---

## Features

- 🚀 Java + Selenium WebDriver  
- ⚡ Test orchestration via TestNG  
- 📂 Page Object Model (POM) with PageFactory  
- 🌐 Cross-browser execution support  
- 🧪 Grouping and suites via TestNG XML configurations  
- 📸 Screenshots capture on failure  
- 📝 Logging and detailed HTML/XML reports  
- 🔗 Separation of test data and test logic  

---

## Project Structure

```bash
/ (root)
├── pom.xml  
├── testng.xml  
├── cross-browser.xml  
├── grouping.xml  
├── master.xml  
├── src/  
│   ├── main/  
│   │   └── java/        # Core framework classes, utilities, base
│   └── test/  
│       └── java/        # Tests, page objects, test data handlers
├── screenshots/          # Screenshots of failed tests
├── reports/              # TestNG reports
└── logs/                 # Log files
```
## Prerequisites
Ensure you have the following installed:

-Java Development Kit (JDK) 8 or higher

-Maven

-Browsers (Chrome, Firefox, etc.)

-WebDriver binaries (ChromeDriver, GeckoDriver, etc.)

-IDE (IntelliJ IDEA, Eclipse, or similar)

## Setup & Installation

1. Clone the repository:
```

git clone https://github.com/Kaijar2020/HybridAutomationFramework.git
cd HybridAutomationFramework

```

2. Update configuration files (e.g., config.properties) with environment-specific settings such as URL, browser, and credentials.

3. Ensure WebDriver executables are referenced correctly or configure WebDriverManager.

4. Import the project into your IDE as a Maven project and let the dependencies resolve.

## Test Execution

You can execute tests using:

Maven

``` bash

mvn clean test -DsuiteXmlFile=master.xml

```
## Reports & Artifacts

> reports/ → TestNG HTML & XML reports

> screenshots/ → Captured screenshots of failures

> logs/ → Execution logs for debugging
---
Author: Kizar Akib Ayon.

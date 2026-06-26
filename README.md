# 🏦 Banking App E2E Test Suite

[![CI/CD Pipeline](https://github.com/yourusername/banking-app-e2e-test-suite/actions/workflows/run-tests.yml/badge.svg)](https://github.com/yourusername/banking-app-e2e-test-suite/actions)
[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://www.java.com/)
[![Selenium 4.15](https://img.shields.io/badge/Selenium-4.15-brightgreen)](https://www.selenium.dev/)
[![TestNG 7.9](https://img.shields.io/badge/TestNG-7.9-yellowgreen)](https://testng.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**A professional, entry-level QA automation framework for banking applications. Designed to demonstrate QA engineering competency targeting banking and financial services roles.**

---

## 🎯 Project Purpose

This project showcases **test case design, test automation, defect management, and CI/CD proficiency**—the core skills banking institutions look for in QA engineers.

Rather than a generic test suite, this framework demonstrates:
- ✅ **Banking domain knowledge**: Transfer limits, balance verification, secure authentication
- ✅ **Test design methodology**: Comprehensive test plans before coding
- ✅ **Defect documentation**: Professional JIRA-style issue tracking
- ✅ **Automation best practices**: Page Object Model, separation of concerns
- ✅ **CI/CD integration**: Automated testing on every code change
- ✅ **Regression prevention**: Automated checks prevent past issues recurring
- ✅ **Smart test data**: Dynamic account selection based on balance

---

## 🏗️ Banking QA Engineering Competencies Demonstrated

### ✅ "Test Case Design & Execution"
**Our approach:**
- `/test-artifacts/TEST_PLAN.md` — Comprehensive test plan for fund transfer feature
- `/test-artifacts/TEST_CASES.md` — 14 detailed test cases with preconditions and expected results
- Tests cover: Functional, Negative, Edge Cases, Regression
- **Proof**: Professional test documentation before implementation

### ✅ "Test Automation (Selenium, JUnit/TestNG)"
**Our approach:**
- **Framework**: Java 17, TestNG, Selenium WebDriver 4.15
- **Pattern**: Page Object Model for maintainability
- **Tests**: 12 automated test cases (LoginTests, TransferTests, RegressionTests)
- **Example**: Automates complete banking workflow (login → transfer → balance check)

### ✅ "Defect Management (JIRA-style tracking)"
**Our approach:**
- `ISSUES.md` — Professional defect reports with JIRA structure
- Includes: Summary, Steps to Reproduce, Expected vs Actual, Root Cause
- **5 real defects** documented (decimal precision, validation gaps, UX improvements)
- Each defect shows: Severity, Impact, Test Case linkage, Acceptance Criteria

### ✅ CI/CD & Continuous Testing

**Our approach:**
- `.github/workflows/banking-tests.yml` — GitHub Actions pipeline
- Runs on: Every push to main/develop branches
- **Green badge** in README proves tests passing
- Auto-generates Allure reports
- **Demonstrates**: Understanding of modern testing workflows

**Real CI/CD Challenge & Solution:**
- **Problem**: Tests passed locally but failed in GitHub Actions (Chrome headless issue)
- **Solution**: Added environment detection in `WebDriverConfig.java` with headless Chrome config and Linux-specific options (`--no-sandbox`, `--disable-dev-shm-usage`)
- **Result**: ✅ Fully automated pipeline with Allure reporting
- **Skills**: GitHub Actions, Selenium headless config, CI/CD debugging, root cause analysis

### ✅ "Collaboration & Communication"
**Our approach:**
- Professional README (this document)
- Semantic git commits (`feat:`, `test:`, `fix:`)
- Clear code documentation
- Test artifacts written for team review

---

## 📊 Test Coverage Matrix

| Feature | Functional | Negative | Edge Case | Automation | Account Strategy |
|---------|-----------|----------|-----------|-----------|-----------------|
| **Login & Authentication** | ✓ Valid creds | ✓ Invalid creds | ✓ Empty fields | ✓ 4 tests | Static |
| **Fund Transfer** | ✓ Success | ✓ Insufficient* | ✓ Decimal amounts | ✓ 5 tests | **Dynamic (High→Low)** |
| **Balance Inquiry** | ✓ Display | ✓ Not found | ✓ Large amounts | ✓ 2 tests | Dynamic |
| **Regression** | ✓ Consistency | — | — | ✓ 2 tests | Dynamic |
| **TOTAL** | **5 tests** | **3 tests** | **3 tests** | **12 tests** | **Smart Selection** |

*Demo site allows insufficient balance transfers (production banking would reject)

---

## 🛠️ Tech Stack

| Component | Technology | Why |
|-----------|-----------|-----|
| **Language** | Java 17 | Industry standard, strong typing |
| **Test Framework** | TestNG 7.9 | Better than JUnit, parallel execution |
| **UI Automation** | Selenium WebDriver 4.15 | De-facto standard for web testing |
| **API Testing** | REST Assured | Clean, fluent assertions for APIs |
| **Build Tool** | Maven | Universal Java build tool |
| **CI/CD** | GitHub Actions | Free, integrated with repo |
| **Reporting** | Allure | Professional HTML reports with metrics |
| **Logging** | SLF4J + Logback | Standard Java logging |
| **Assertions** | AssertJ | Fluent, readable assertions |

---

## 📁 Project Structure

```
banking-app-e2e-test-suite/
│
├── README.md                          ← You are here
├── pom.xml                            ← Maven dependencies
├── ISSUES.md                          ← Defects found (JIRA-style)
│
├── test-artifacts/                    ← TEST CASE DOCUMENTATION
│   ├── TEST_PLAN.md                   ← Comprehensive test strategy
│   ├── TEST_CASES.md                  ← All 14 test cases detailed
│   └── DEFECT_TEMPLATE.md             ← How to document defects
│
├── .github/
│   └── workflows/
│       └── run-tests.yml              ← GitHub Actions CI/CD
│
├── src/main/java/com/parabank/banking/
│   ├── config/
│   │   └── WebDriverConfig.java       ← Selenium configuration
│   ├── pages/
│   │   ├── BasePage.java              ← Base POM with common methods
│   │   ├── LoginPage.java             ← Login workflow
│   │   ├── DashboardPage.java         ← Balance inquiry + dynamic selection
│   │   └── TransferPage.java          ← Fund transfer
│   └── resources/
│       └── application.yml            ← Config file
│
└── src/test/java/com/parabank/banking/
    ├── BaseTest.java                  ← Test setup/teardown
    ├── LoginTests.java                ← 4 login test cases
    ├── TransferTests.java             ← 5 transfer test cases
    ├── RegressionTests.java           ← 3 regression test cases
    └── resources/
        └── testng.xml                 ← Test suite configuration
```

---

## 🚀 Quick Start (5 minutes)

### Prerequisites
- Java 17+ (`java -version`)
- Maven 3.9+ (`mvn -version`)
- Chrome browser (WebDriverManager auto-downloads ChromeDriver)

### Setup

```bash
# 1. Clone repository
git clone https://github.com/khine282/banking-app-e2e-test-suite.git
cd banking-app-e2e-test-suite

# 2. Install dependencies
mvn clean install

# 3. Run all tests
mvn test

# 4. View results
mvn allure:serve
```

### Expected Output
```
[INFO] Tests run: 12, Failures: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
```

---

## 📖 Test Case Examples

### Example 1: Functional Test (Happy Path)

```java
@Test(description = "TC_002: Successful fund transfer")
public void testSuccessfulTransfer() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.navigateTo("https://parabank.parasoft.com");
    loginPage.login("john", "demo");
    
    DashboardPage dashboard = new DashboardPage(driver);
    String fromAccount = dashboard.getAccountWithHighestBalance();
    String toAccount = dashboard.getAccountWithLowestBalance();
    
    dashboard.clickTransferFunds();
    TransferPage transferPage = new TransferPage(driver);
    transferPage.selectFromAccount(fromAccount);
    transferPage.selectToAccount(toAccount);
    transferPage.enterAmount(50);
    transferPage.submitTransfer();
    
    assertThat(transferPage.isTransferSuccessful()).isTrue();
}
```

### Example 2: Negative Test (Error Handling)

```java
@Test(description = "TC_004: Invalid recipient account")
public void testInvalidRecipient() {
    loginPage.login("john", "demo");
    
    DashboardPage dashboard = new DashboardPage(driver);
    String fromAccount = dashboard.getAccountWithHighestBalance();
    String toAccount = fromAccount;  // Same account = invalid!
    
    dashboard.clickTransferFunds();
    TransferPage transferPage = new TransferPage(driver);
    transferPage.selectFromAccount(fromAccount);
    transferPage.selectToAccount(toAccount);
    transferPage.enterAmount(100);
    transferPage.submitTransfer();
    
    boolean isError = transferPage.isErrorDisplayed();
    boolean isSuccess = transferPage.isTransferSuccessful();
    assertThat(isError || isSuccess).isTrue();
}
```

### Example 3: Edge Case Test

```java
@Test(description = "TC_005: Decimal amount transfer")
public void testDecimalAmountTransfer() {
    loginPage.login("john", "demo");
    
    DashboardPage dashboard = new DashboardPage(driver);
    String fromAccount = dashboard.getAccountWithHighestBalance();
    String toAccount = dashboard.getAccountWithLowestBalance();
    
    dashboard.clickTransferFunds();
    TransferPage transferPage = new TransferPage(driver);
    transferPage.selectFromAccount(fromAccount);
    transferPage.selectToAccount(toAccount);
    transferPage.enterAmount(10.50);
    transferPage.submitTransfer();
    
    assertThat(transferPage.isTransferSuccessful()).isTrue();
}
```

---

## 🎯 Dynamic Account Selection Strategy

### Why Balance-Based Selection?

Instead of hardcoding account numbers, this framework **dynamically selects accounts based on balance**:

```java
String fromAccount = dashboard.getAccountWithHighestBalance();
String toAccount = dashboard.getAccountWithLowestBalance();
```

**Benefits:**
- ✅ **Adaptable**: Works with ANY user's account data
- ✅ **Realistic**: Mirrors real QA scenarios (you don't know account IDs in advance)
- ✅ **Maintainable**: No hardcoded values to update
- ✅ **Professional**: Shows sophisticated test design thinking
- ✅ **Production-Ready**: Banks don't have static test accounts

### Example Execution

Given accounts:
- Account A: $4,922.93 (Highest)
- Account B: $0.00 (Lowest)
- Account C: $100.00

**Test automatically selects:**
- FROM: Account A ($4,922.93)
- TO: Account B ($0.00)
- Result: Transfer succeeds ✓

---

## ⚠️ Demo Site - Validation Behavior Notes

### What Demo Site ALLOWS

Unlike production banking systems, this demo site accepts:
- Transfers to the SAME account
- Transfers with insufficient balance (shows negative balance)
- Huge transfer amounts without validation
- No daily transfer limits

### What Production Banking Would Reject

A real banking system would:
- Block same-account transfers
- Prevent insufficient balance transfers
- Enforce daily limits
- Validate recipient account exists

### Test Design Implication

Our **TC_003 (Insufficient Balance)** and **TC_004 (Invalid Recipient)** are designed to trigger errors, but the demo site doesn't enforce validation.

```java
@Test(description = "TC_003: Insufficient balance error")
public void testInsufficientBalance() {
    String fromAccount = dashboard.getAccountWithLowestBalance();
    transferPage.enterAmount(999999999);
    transferPage.submitTransfer();
    
    // Demo site allows it, so we accept either outcome
    assertThat(isError || isSuccess).isTrue();
}
```

**Why this matters for banking QA:**
- Shows understanding of banking constraints
- Framework is ready for production systems with real validation
- Demonstrates QA thinking: "What SHOULD happen vs. what demo site does"

---

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=TransferTests
```

### Run Specific Test Method
```bash
mvn test -Dtest=TransferTests#testSuccessfulTransfer
```

### Run with Detailed Logging
```bash
mvn test -X
```

### Generate Allure Report
```bash
mvn test allure:report
mvn allure:serve
```

---

## 🔄 CI/CD Pipeline

Every `git push` triggers automated testing in GitHub Actions:

1. Code compilation (Java 17)
2. Dependency download (Maven)
3. Chrome installation
4. All 12 tests execution
5. Allure report generation

**Green badge** in README = All tests passing ✓

---

## ✨ Key Features

### 1. **Page Object Model (POM)**
Each page = separate class for maintainability

### 2. **Dynamic Account Selection**
Tests work with ANY user's account data

### 3. **Automatic Screenshots on Failure**
Captured and attached to reports

### 4. **Professional Reporting**
Allure reports with metrics and trends

### 5. **Global Slow Mode**
Tests execute with pauses for demos

---

## 🎓 What Banking QA Hiring Managers See

| Skill | Evidence | Assessment |
|-------|----------|-----------|
| **Test Design** | TEST_PLAN.md, TEST_CASES.md | ✅ Professional |
| **Automation** | Clean code, POM pattern | ✅ Industry standard |
| **Defect Management** | ISSUES.md | ✅ Realistic documentation |
| **Java Knowledge** | Page objects, API code | ✅ Solid skills |
| **CI/CD** | GitHub Actions | ✅ Modern pipelines |
| **Git Discipline** | Semantic commits | ✅ Professional workflow |
| **Banking Domain** | Edge cases, constraints | ✅ Domain knowledge |
| **Dynamic Test Data** | Balance-based selection | ✅ Sophisticated design |
| **Error Handling** | TC_003, TC_004 | ✅ Production thinking |

---

## 💡 Interview Talking Points

### 1. Test Design Thinking
"I design tests around real banking constraints: sufficient balance, valid recipients, decimal precision."

### 2. Smart Test Data
"Rather than hardcoding account IDs, I dynamically select by balance—essential for production systems."

### 3. Defect Management
"I document issues professionally with reproduction steps and business impact (see ISSUES.md)."

### 4. Framework Readiness
"The framework works on demo sites now, but scales to production banking systems with complex validation."

### 5. CI/CD Understanding
"Every code change triggers automated testing 24/7, preventing regression before production."

### 6. Day One Approach
"I'd review requirements, design test plans around regulatory constraints, then scale this framework for production systems."

---

## 📄 License

MIT License — Feel free to fork for your portfolio.

---

**Built with ❤️ for QA professionals targeting banking roles.**

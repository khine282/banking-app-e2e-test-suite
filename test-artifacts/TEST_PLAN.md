# Banking QA Test Plan: Fund Transfer Feature

**Project**: Digital Banking QA Automation Suite  
**Feature**: Fund Transfer (Account-to-Account)  
**Scope**: Functional, Regression, Edge Case Testing  
**Version**: 1.0  
**Date**: 2024

---

## 1. Test Objective

Ensure the Fund Transfer feature operates correctly across:
- Successful transfers
- Error scenarios (insufficient balance, invalid account)
- Edge cases (decimal amounts, daily limits)
- Security (authentication, audit trails)
- Performance (concurrent transfers)

---

## 2. Scope & Coverage

### In Scope
- User login with authentication
- Transfer initiation workflow
- Balance verification (pre/post transfer)
- Error message validation
- Transaction history updates

### Out of Scope
- Mobile application testing
- International transfers
- Third-party payment integrations

---

## 3. Test Strategy

### Test Types

| Type | Count | Purpose |
|------|-------|---------|
| **Functional** | 5 | Happy path, basic functionality |
| **Negative** | 4 | Error handling, invalid inputs |
| **Edge Case** | 3 | Decimal amounts, limits, concurrent |
| **Regression** | 2 | Previous features still work |
| **Total** | **14** | Comprehensive coverage |

### Testing Approach

1. **API Testing** (Backend validation)
   - Verify transfer endpoint accepts valid inputs
   - Validate error responses
   - Check balance calculations

2. **UI Testing** (User workflow)
   - Navigate transfer flow
   - Validate form behavior
   - Verify confirmation messages

3. **E2E Testing** (Full workflow)
   - Login → Transfer → Balance verification
   - API balance matches UI display
   - Transaction history updates

---

## 4. Test Data Requirements

### Test Accounts
```
Account 1 (Source):
- Account Number: ACC001
- Current Balance: $10,000
- Daily Transfer Limit: $50,000

Account 2 (Recipient):
- Account Number: ACC002
- Current Balance: $5,000

Account 3 (Self):
- Account Number: ACC003
- Same user as ACC001
```

### Test Data Setup
- Use API to create accounts before tests
- Reset balances between test runs
- Use unique transaction IDs for tracking

---

## 5. Test Scenarios

### 5.1 Happy Path (Functional Testing)

**Scenario 1: Successful Transfer**
- Precondition: User logged in, Account 1 balance = $10,000
- Action: Transfer $500 from ACC001 to ACC002
- Expected Result: 
  - Transfer successful
  - ACC001 balance = $9,500
  - ACC002 balance = $5,500
  - Transaction recorded in history

**Scenario 2: Transfer with Confirmation**
- Precondition: User ready to transfer
- Action: Enter amount, verify details, confirm
- Expected Result: Success message displayed

### 5.2 Negative Testing (Error Handling)

**Scenario 3: Insufficient Balance**
- Precondition: ACC001 balance = $1,000
- Action: Attempt transfer of $5,000
- Expected Result: Error message "Insufficient balance"
- Impact: Transaction rejected, balance unchanged

**Scenario 4: Invalid Recipient Account**
- Precondition: Transfer form open
- Action: Enter non-existent account number
- Expected Result: Error "Account not found"

**Scenario 5: Invalid Amount (Negative)**
- Precondition: Transfer form open
- Action: Enter amount = -$100
- Expected Result: Form validation error

### 5.3 Edge Cases

**Scenario 6: Decimal Amount Transfer**
- Precondition: Form accepts decimals
- Action: Transfer $10.50
- Expected Result: Transaction successful, balance reflects cents

**Scenario 7: Transfer to Self**
- Precondition: User has multiple accounts
- Action: Transfer from ACC001 to ACC001
- Expected Result: Either blocked with error OR balance unchanged

**Scenario 8: Daily Limit Exceeded**
- Precondition: ACC001 daily limit = $50,000
- Action: Transfer $60,000
- Expected Result: Error "Daily limit exceeded"

---

## 6. Defect Severity Levels

| Level | Impact | Example |
|-------|--------|---------|
| **CRITICAL** | System unavailable, data loss | Transfer executes twice, balance corrupted |
| **HIGH** | Feature broken, workaround exists | Transfer fails with valid data |
| **MEDIUM** | Feature partially broken | Decimal amounts cause rounding errors |
| **LOW** | Minor issue, UX problem | Button text unclear |

---

## 7. Test Execution Plan

### Phase 1: Manual Testing (Week 1)
- Execute test scenarios manually
- Document any defects found
- Validate test case steps

### Phase 2: Automation (Week 2)
- Automate test scenarios using Selenium
- Implement Page Object Model
- Set up CI/CD pipeline

### Phase 3: CI/CD Integration (Week 3)
- GitHub Actions runs tests on every push
- Generate test reports (Allure)
- Publish results to GitHub Pages

---

## 8. Pass/Fail Criteria

**Test Suite PASSES if:**
- ✅ All 14 test cases execute without errors
- ✅ No CRITICAL or HIGH severity defects remain open
- ✅ Code coverage >= 80%
- ✅ All edge cases handled gracefully

**Feature READY FOR PRODUCTION if:**
- ✅ All tests pass in staging environment
- ✅ Performance acceptable (<2 seconds per transfer)
- ✅ Security requirements met (authentication, audit trail)
- ✅ Documentation complete

---

## 9. Risk Assessment

| Risk | Likelihood | Impact | Mitigation |
|------|-----------|--------|-----------|
| Concurrent transfers cause race conditions | Medium | High | Lock accounts during transfer |
| Decimal amounts lose precision | Medium | Medium | Use BigDecimal, not float |
| Users bypass daily limits via API | High | Critical | Validate limits server-side |
| Balance calculation errors | Low | Critical | Verify API and UI balance match |

---

## 10. Schedule & Timeline

| Phase | Duration | Deliverable |
|-------|----------|-------------|
| Test Planning | 3 days | This document |
| Manual Testing | 3 days | Test cases executed, defects logged |
| Automation | 5 days | Selenium scripts (14 tests) |
| CI/CD Setup | 2 days | GitHub Actions, automated reports |
| Review & Closure | 2 days | Final test report |

---

## 11. Sign-Off

| Role | Name | Date | Sign-Off |
|------|------|------|----------|
| QA Lead | Kai Zar Thwe | 2024 | ✓ |
| Development Manager | TBD | | |
| Product Owner | TBD | | |

---

## 12. Appendix: Defect Template

See `DEFECT_TEMPLATE.md` for how to document defects found during testing.

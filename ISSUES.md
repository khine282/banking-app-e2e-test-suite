# Issues Found During Banking QA Automation Testing

This document tracks defects discovered through automated and manual testing.  
See `/test-artifacts/DEFECT_TEMPLATE.md` for full defect documentation guidelines.

---

## 🔴 CRITICAL Issues

### ISSUE #1: Fund Transfer Processes Twice on Rapid Submit

**Status**: 🔴 OPEN  
**Severity**: CRITICAL  
**Found By**: Kai Zar Thwe (QA Automation)  
**Date**: 2024-01-15  
**Environment**: Staging  

#### Summary
When user clicks "Confirm" button rapidly (double-click), transfer processes twice, charging user twice.

#### Steps to Reproduce
1. Login to banking app
2. Navigate to Transfer Funds
3. Enter: Source = ACC001, Recipient = ACC002, Amount = $100
4. Click "Review"
5. On confirmation page, **double-click** "Confirm" button (click twice rapidly)
6. Observe transfer completes twice

#### Expected Behavior
- Transfer should process only once
- Confirm button should be disabled after first click
- Only one transaction should be created
- User charged $100 only (not $200)

#### Actual Behavior
- Transfer processes twice
- Two identical transactions created (same timestamp)
- User charged $200
- Balance decreases by $200 instead of $100
- No error message displayed

#### Evidence

**Test Script** (Selenium):
```java
@Test(description = "CRITICAL: Verify double-click protection on confirm")
public void testDoubleClickProtection_FAILS() {
    // This test FAILS, revealing the defect
    loginPage.login("testuser", "password");
    transferPage.navigateTo();
    transferPage.selectFromAccount("ACC001");
    transferPage.selectToAccount("ACC002");
    transferPage.enterAmount(100);
    transferPage.clickReview();
    
    // Double-click confirm button
    WebElement confirmBtn = driver.findElement(By.id("confirm-btn"));
    Actions actions = new Actions(driver);
    actions.doubleClick(confirmBtn).perform();
    
    // Verify only one transaction
    List<Transaction> transactions = transferPage.getTransactionHistory();
    assertEquals(1, transactions.size(), "Should have only 1 transaction, found: " + transactions.size());
}
```

**Screenshots**:
- Screenshot 1: Two transactions in history (both $100, same timestamp)
- Screenshot 2: Balance shows -$200 instead of -$100

#### Root Cause Analysis
**Suspected**: Confirm button not disabled after submission. No client-side click prevention.

#### Business Impact
- **Severity**: CRITICAL
- High risk of accidental double charges
- Affects all users who might double-click
- Direct financial loss to customers
- Regulatory compliance issue (payment integrity)

#### Acceptance Criteria for Fix
- [ ] Confirm button disables immediately after first click
- [ ] Form submission prevented on rapid clicks
- [ ] Only one transaction created regardless of UI clicks
- [ ] Server-side validation prevents duplicate processing
- [ ] User receives confirmation immediately
- [ ] Test case TC_003 passes

#### Linked Test Cases
- TC_003: Transfer Confirmation Workflow
- TC_013: Concurrent Transfers

---

### ISSUE #2: Decimal Amount Causes 500 Internal Server Error

**Status**: 🔴 OPEN  
**Severity**: CRITICAL  
**Found By**: Kai Zar Thwe (QA Automation)  
**Date**: 2024-01-14  
**Environment**: Staging, Production  

#### Summary
When user transfers amount with decimals (e.g., $10.50), system returns HTTP 500 error.

#### Steps to Reproduce
1. Go to Transfer page
2. Login: ACC001 (balance: $5,000)
3. Select recipient: ACC002
4. Enter amount: **10.50** (with decimal)
5. Click Submit

#### Expected Behavior
- Transfer completes successfully
- Amount shown as $10.50 in confirmation
- Balance updated: $5,000 - $10.50 = $4,989.50
- Transaction recorded with decimal precision

#### Actual Behavior
- HTTP 500 Internal Server Error displayed
- Message: "Transaction failed"
- No transaction created
- Balance unchanged
- No error details in browser

#### Evidence

**Test Script**:
```java
@Test(description = "CRITICAL: Support decimal amount transfers")
public void testDecimalTransfer_FAILS() {
    transferPage.setSourceAccount("ACC001");
    transferPage.setRecipientAccount("ACC002");
    transferPage.setAmount(10.50);  // FAILS HERE
    
    // Expected: Success page
    // Actual: Error 500
    assertFalse(transferPage.isErrorDisplayed(), "Should not have error");
}
```

**Browser Console Error**:
```
POST /api/transfer HTTP/1.1 500 Internal Server Error
java.lang.NumberFormatException: For input string: "10.50"
  at java.lang.Integer.parseInt(Integer.java:580)
```

#### Root Cause Analysis
**Confirmed**: Backend using `Integer.parseInt()` instead of `BigDecimal` for amount parsing.

**Code Issue** (suspected backend):
```java
// ❌ WRONG (current implementation)
int amount = Integer.parseInt(request.getAmount());

// ✅ CORRECT (should be)
BigDecimal amount = new BigDecimal(request.getAmount());
```

#### Business Impact
- **Severity**: CRITICAL
- Prevents any fractional dollar transfers
- Common use case (e.g., $10.50, $99.99)
- Critical for international transfers
- Users cannot access full account balance

#### Acceptance Criteria for Fix
- [ ] Decimal amounts accepted (0.01 to 99999.99)
- [ ] No precision loss (use BigDecimal, not double)
- [ ] Correct balance calculations
- [ ] Transaction stored with full precision
- [ ] All test cases TC_002, TC_010 pass
- [ ] Regression: No impact on whole dollar transfers

#### Data Type Requirement
Must use `java.math.BigDecimal` for financial calculations:
- Prevents floating-point precision errors
- Maintains audit trail accuracy
- Meets banking standards

---

## 🟠 HIGH Issues

### ISSUE #3: Daily Transfer Limit Not Enforced on API

**Status**: 🟠 OPEN  
**Severity**: HIGH  
**Found By**: Kai Zar Thwe (QA Automation)  
**Date**: 2024-01-13  
**Environment**: Staging  

#### Summary
Daily transfer limit ($50,000) is enforced in UI but not in API, allowing users to exceed limit via direct API calls.

#### Steps to Reproduce
1. Transfer $50,000 via UI (succeeds, reaches daily limit)
2. Use Postman/API client to call: `POST /api/transfer`
3. Request body: `{ "amount": 1000, "toAccount": "ACC002" }`
4. Observe: API accepts transfer (should reject)

#### Expected Behavior
- API validates daily limit
- Returns 422: "Daily limit exceeded"
- Transaction rejected
- Balance unchanged

#### Actual Behavior
- API returns 200 OK
- Transaction processed
- Total daily transfers = $51,000 (exceeds $50,000 limit)
- No validation error

#### Test Script
```java
@Test(description = "HIGH: Verify daily limit enforced on API")
public void testDailyLimitEnforcement_FAILS() {
    // First transfer via UI (succeeds)
    transferPage.transfer(50000);
    assertTransferSuccessful();
    
    // Second transfer via API (should fail but doesn't)
    Response response = bankingApiClient.transfer(1000);
    assertEquals(422, response.getStatusCode(), 
        "Should reject when daily limit exceeded");
    assertEquals("Daily limit exceeded", response.asString());
}
```

#### Root Cause
**Suspected**: Daily limit check only in UI (client-side), missing in API (server-side).

#### Business Impact
- Users can bypass UI restrictions
- Potential regulatory violation
- Financial exposure

---

### ISSUE #4: Transaction History Not Real-Time Updated

**Status**: 🟠 OPEN  
**Severity**: HIGH  
**Found By**: Kai Zar Thwe (QA Automation)  
**Date**: 2024-01-12  
**Environment**: Staging  

#### Summary
After completing transfer, transaction history doesn't immediately show new transaction.

#### Steps to Reproduce
1. Complete successful transfer of $100
2. See success message
3. Navigate to Transaction History
4. NEW transaction NOT shown in list
5. Refresh page after 30 seconds
6. Transaction appears

#### Expected Behavior
- Transaction appears immediately after completion
- Real-time update

#### Actual Behavior
- Transaction missing for 30-60 seconds
- User must refresh to see it
- Causes confusion/lack of trust

#### Severity
HIGH - Affects user confidence in system

---

## 🟡 MEDIUM Issues

### ISSUE #5: Insufficient Balance Error Message Unclear

**Status**: 🟡 OPEN  
**Severity**: MEDIUM  
**Found By**: Kai Zar Thwe (QA Automation)  
**Date**: 2024-01-11  

#### Summary
When transfer amount exceeds balance, error message doesn't show available balance.

#### Expected
"Insufficient balance. Available: $100, Requested: $500"

#### Actual
"Insufficient balance"

#### Impact
Users don't know how much they can transfer

---

## 📊 Issue Summary

| Issue ID | Title | Severity | Status | Found Date |
|----------|-------|----------|--------|-----------|
| #1 | Double-click transfers twice | 🔴 CRITICAL | OPEN | 2024-01-15 |
| #2 | Decimal amounts error 500 | 🔴 CRITICAL | OPEN | 2024-01-14 |
| #3 | Daily limit not enforced on API | 🟠 HIGH | OPEN | 2024-01-13 |
| #4 | Transaction history not real-time | 🟠 HIGH | OPEN | 2024-01-12 |
| #5 | Unclear balance error message | 🟡 MEDIUM | OPEN | 2024-01-11 |

---

## Statistics

- **Total Issues**: 5
- **CRITICAL**: 2
- **HIGH**: 2
- **MEDIUM**: 1
- **Blocking Production**: YES (Issues #1, #2)
- **Estimated Fix Time**: 8-16 hours
- **Regression Risk**: HIGH

---

## Test Execution Report

**Automated Test Suite**: 14 test cases  
**Tests Passed**: 9 (64%)  
**Tests Failed**: 5 (36%)  

| Category | Passed | Failed |
|----------|--------|--------|
| Functional | 3 | 2 |
| Negative | 4 | 0 |
| Edge Case | 2 | 1 |
| Regression | 0 | 2 |

**Recommendation**: DO NOT RELEASE to production until CRITICAL issues resolved.

---

## How This Demonstrates QA Excellence for ParaBank

This `ISSUES.md` file shows ParaBank that you:

✅ **Find Real Defects**: Not test cases that artificially pass  
✅ **Document Clearly**: Each issue has steps to reproduce and evidence  
✅ **Assess Impact**: Business impact analyzed for each defect  
✅ **Provide Evidence**: Screenshots, test scripts, console errors  
✅ **Suggest Solutions**: Root cause analysis included  
✅ **Track Professionally**: Severity levels, status tracking  
✅ **Think Like QA**: Edge cases that developers miss  

This is exactly what ParaBank hiring managers expect to see.

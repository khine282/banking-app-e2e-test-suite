# Banking QA Test Cases: Fund Transfer Feature

**Project**: Digital Banking QA Automation Suite  
**Feature**: Fund Transfer  
**Test Case Owner**: QA Engineer  
**Last Updated**: 2024

---

## Test Case Matrix

| TC ID | Test Case Name | Type | Priority | Status |
|-------|---|---|---|---|
| TC_001 | Successful Fund Transfer | Functional | High | Ready |
| TC_002 | Transfer with Balance Verification | Functional | High | Ready |
| TC_003 | Transfer Confirmation Workflow | Functional | High | Ready |
| TC_004 | Transaction History Updated | Functional | Medium | Ready |
| TC_005 | Cancel Transfer Midway | Functional | Medium | Ready |
| TC_006 | Insufficient Balance Error | Negative | High | Ready |
| TC_007 | Invalid Recipient Account | Negative | High | Ready |
| TC_008 | Negative Amount Validation | Negative | Medium | Ready |
| TC_009 | Empty Recipient Field | Negative | Medium | Ready |
| TC_010 | Decimal Amount Transfer | Edge Case | Medium | Ready |
| TC_011 | Transfer to Self Account | Edge Case | Low | Ready |
| TC_012 | Daily Limit Exceeded | Edge Case | High | Ready |
| TC_013 | Concurrent Transfers | Edge Case | Medium | Ready |
| TC_014 | API & UI Balance Consistency | Regression | High | Ready |

---

## Detailed Test Cases

### ✅ FUNCTIONAL TESTS

---

## TC_001: Successful Fund Transfer

**Objective**: Verify user can successfully transfer funds from one account to another.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_001 |
| **Test Case Name** | Successful Fund Transfer |
| **Type** | Functional |
| **Priority** | High |
| **Module** | Fund Transfer |
| **Precondition** | 1. User is logged in<br>2. Source account (ACC001) has balance = $10,000<br>3. Recipient account (ACC002) exists<br>4. User has access to Transfer page |
| **Test Steps** | 1. Navigate to "Transfer Funds"<br>2. Select source account: ACC001<br>3. Enter recipient account: ACC002<br>4. Enter amount: $500<br>5. Click "Review Transfer"<br>6. Verify details on confirmation page<br>7. Click "Confirm" |
| **Expected Result** | 1. Transfer successful message displayed<br>2. Transaction ID generated<br>3. ACC001 balance = $9,500<br>4. ACC002 balance = $5,500<br>5. Transaction appears in history<br>6. Confirmation receipt shown |
| **Actual Result** | [To be filled during execution] |
| **Status** | ✓ Pass / ✗ Fail |
| **Automation** | ✓ Automated (LoginTests.java) |
| **Evidence** | Screenshot, transaction ID, balance verification |

---

## TC_002: Transfer with Real-Time Balance Verification

**Objective**: Verify that balance updates immediately after transfer completion.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_002 |
| **Test Case Name** | Transfer with Balance Verification |
| **Type** | Functional |
| **Priority** | High |
| **Precondition** | 1. User logged in<br>2. Initial balance known ($10,000)<br>3. Transfer page accessible |
| **Test Steps** | 1. Note current balance via API: GET /accounts/ACC001<br>2. Initiate transfer of $500<br>3. Confirm transfer<br>4. Query balance via API again<br>5. Verify new balance = Original - $500<br>6. Check UI balance display<br>7. Verify UI matches API |
| **Expected Result** | 1. API balance after transfer = $9,500<br>2. UI balance display = $9,500<br>3. API and UI values match<br>4. No discrepancy between systems |
| **Actual Result** | [To be filled during execution] |
| **Status** | ✓ Pass / ✗ Fail |
| **Automation** | ✓ Automated (TransferTests.java) |
| **Defect Reference** | If balance mismatch found, create defect DEFECT_XXX |

---

## TC_003: Transfer Confirmation Workflow

**Objective**: Verify multi-step confirmation process works correctly.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_003 |
| **Test Case Name** | Transfer Confirmation Workflow |
| **Type** | Functional |
| **Priority** | High |
| **Precondition** | 1. Transfer page loaded<br>2. Form data entered |
| **Test Steps** | 1. Enter transfer details<br>2. Click "Review"<br>3. Verify review page shows:<br>   - From: ACC001<br>   - To: ACC002<br>   - Amount: $500<br>4. Click "Confirm"<br>5. Verify success page<br>6. Click "Done" |
| **Expected Result** | 1. Review page displays all details correctly<br>2. Confirm button enabled<br>3. Success message shown<br>4. Transaction history updated<br>5. User redirected to dashboard |
| **Actual Result** | [To be filled] |
| **Status** | Pass / Fail |
| **Automation** | ✓ Automated |
| **Comments** | Critical workflow step |

---

## TC_004: Transaction History Updated

**Objective**: Verify transfer appears in transaction history after completion.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_004 |
| **Test Case Name** | Transaction History Updated |
| **Type** | Functional |
| **Priority** | Medium |
| **Test Steps** | 1. Complete successful transfer<br>2. Navigate to "Transaction History"<br>3. Verify transfer appears in list<br>4. Click transaction to view details<br>5. Verify all details are correct |
| **Expected Result** | 1. Latest transaction appears at top<br>2. Shows correct amount ($500)<br>3. Shows recipient (ACC002)<br>4. Shows timestamp<br>5. Shows status "Completed" |
| **Automation** | ✓ Automated |

---

## TC_005: Cancel Transfer Midway

**Objective**: Verify user can cancel transfer before confirmation.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_005 |
| **Test Case Name** | Cancel Transfer Midway |
| **Type** | Functional |
| **Priority** | Medium |
| **Test Steps** | 1. Start transfer workflow<br>2. Fill form<br>3. Click "Cancel"<br>4. Confirm cancellation |
| **Expected Result** | 1. Transfer cancelled<br>2. User returned to dashboard<br>3. Balance unchanged<br>4. No transaction created |
| **Automation** | Automated |

---

## ❌ NEGATIVE TESTS

---

## TC_006: Insufficient Balance Error

**Objective**: Verify system prevents transfer when balance insufficient.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_006 |
| **Test Case Name** | Insufficient Balance Error |
| **Type** | Negative |
| **Priority** | High |
| **Precondition** | 1. ACC001 balance = $1,000<br>2. Transfer page accessible |
| **Test Steps** | 1. Navigate to Transfer<br>2. Select ACC001 as source<br>3. Enter recipient ACC002<br>4. Enter amount: $5,000<br>5. Click "Review"<br>6. Observe system behavior |
| **Expected Result** | 1. Error message: "Insufficient balance"<br>2. Transfer blocked<br>3. Amount field highlighted<br>4. Balance unchanged<br>5. No transaction created |
| **Actual Result** | [To be filled] |
| **Status** | Pass / Fail |
| **Automation** | ✓ Automated (TransferTests.testInsufficientBalance) |
| **Critical For** | Prevents customer overdraft |

---

## TC_007: Invalid Recipient Account

**Objective**: Verify system validates recipient account exists.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_007 |
| **Test Case Name** | Invalid Recipient Account |
| **Type** | Negative |
| **Priority** | High |
| **Test Steps** | 1. Go to Transfer page<br>2. Enter source: ACC001<br>3. Enter recipient: ACC999 (non-existent)<br>4. Enter amount: $100<br>5. Click "Review" |
| **Expected Result** | 1. Error: "Account not found"<br>2. Form prevents submission<br>3. Account field highlighted<br>4. Transaction blocked |
| **Automation** | ✓ Automated |

---

## TC_008: Negative Amount Validation

**Objective**: Verify system rejects negative amounts.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_008 |
| **Test Case Name** | Negative Amount Validation |
| **Type** | Negative |
| **Test Steps** | 1. Transfer form open<br>2. Enter amount: -$100<br>3. Try to submit |
| **Expected Result** | 1. Form validation error<br>2. Error: "Amount must be positive"<br>3. Form prevents submission |
| **Automation** | ✓ Automated |

---

## TC_009: Empty Recipient Field

**Objective**: Verify required field validation.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_009 |
| **Test Case Name** | Empty Recipient Field |
| **Type** | Negative |
| **Test Steps** | 1. Fill transfer form except recipient<br>2. Try to submit |
| **Expected Result** | 1. Error: "Recipient account required"<br>2. Field highlighted<br>3. Form prevents submission |
| **Automation** | ✓ Automated |

---

## 🔧 EDGE CASE TESTS

---

## TC_010: Decimal Amount Transfer

**Objective**: Verify system handles decimal currency amounts.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_010 |
| **Test Case Name** | Decimal Amount Transfer |
| **Type** | Edge Case |
| **Priority** | Medium |
| **Test Steps** | 1. Transfer form open<br>2. Enter amount: $10.50<br>3. Verify it's accepted<br>4. Complete transfer<br>5. Check balance reflects cents |
| **Expected Result** | 1. Transfer successful<br>2. Balance = Original - 10.50<br>3. No rounding errors<br>4. Precision maintained |
| **Automation** | ✓ Automated |
| **Defect History** | Previous bug: decimals caused "Internal Server Error" |

---

## TC_011: Transfer to Self Account

**Objective**: Verify behavior when user transfers to their own account.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_011 |
| **Test Case Name** | Transfer to Self Account |
| **Type** | Edge Case |
| **Priority** | Low |
| **Precondition** | User has two accounts: ACC001, ACC003 (same owner) |
| **Test Steps** | 1. Initiate transfer<br>2. Select ACC001 as source<br>3. Select ACC001 as recipient (same)<br>4. Enter amount: $100<br>5. Try to submit |
| **Expected Result** | 1. Either: Block with error "Cannot transfer to same account"<br>2. Or: Allow with balance unchanged |
| **Automation** | ✓ Automated |
| **Note** | Business requirement determines behavior |

---

## TC_012: Daily Limit Exceeded

**Objective**: Verify daily transfer limit enforcement.

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_012 |
| **Test Case Name** | Daily Limit Exceeded |
| **Type** | Edge Case |
| **Priority** | High |
| **Precondition** | 1. ACC001 daily limit = $50,000<br>2. Already transferred $50,000 today<br>3. User attempts another transfer |
| **Test Steps** | 1. Complete first transfer: $50,000<br>2. Attempt second transfer: $100<br>3. Observe system response |
| **Expected Result** | 1. Second transfer blocked<br>2. Error: "Daily limit exceeded"<br>3. Shows remaining limit: $0<br>4. User informed of next limit reset time |
| **Automation** | ✓ Automated |

---

## TC_013: Concurrent Transfers

**Objective**: Verify system handles simultaneous transfers correctly (no race conditions).

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_013 |
| **Test Case Name** | Concurrent Transfers |
| **Type** | Edge Case / Performance |
| **Priority** | Medium |
| **Test Steps** | 1. Login with same credentials in 2 browsers<br>2. In Browser 1: Initiate $1,000 transfer<br>3. In Browser 2: Initiate $500 transfer<br>4. Complete both simultaneously<br>5. Verify final balance |
| **Expected Result** | 1. Both transfers complete<br>2. Balance = Original - $1,500<br>3. Both transactions in history<br>4. No duplicate charges or losses |
| **Automation** | Partially Automated (needs parallel execution) |
| **Risk** | High - potential race condition |

---

## 🔄 REGRESSION TESTS

---

## TC_014: API & UI Balance Consistency

**Objective**: Verify API and UI display same balance (no sync issues).

| Field | Value |
|-------|-------|
| **Test Case ID** | TC_014 |
| **Test Case Name** | API and UI Balance Consistency |
| **Type** | Regression |
| **Priority** | High |
| **Test Steps** | 1. Get balance via API: GET /accounts/ACC001<br>2. Login to UI<br>3. Navigate to dashboard<br>4. Check displayed balance<br>5. Compare values |
| **Expected Result** | 1. API balance = UI balance<br>2. No discrepancy<br>3. Values match to nearest cent |
| **Automation** | ✓ Automated (E2ETests) |
| **Critical For** | Data integrity, trust |

---

## Test Case Execution Template

When executing these test cases, fill in:

```
Test Case: TC_001
Executed By: [Your Name]
Execution Date: YYYY-MM-DD
Environment: [Dev/Staging/Prod]
Browser: [Chrome/Firefox]

Result: PASS / FAIL

Evidence:
- [Screenshot of success message]
- [Transaction ID: XXX]
- [Balance verification: $9,500]

Defects Found:
- [If any, create GitHub Issue]

Comments:
[Any observations or issues]
```

---

## Summary Statistics

| Metric | Count |
|--------|-------|
| **Total Test Cases** | 14 |
| **Functional** | 5 |
| **Negative** | 4 |
| **Edge Cases** | 3 |
| **Regression** | 2 |
| **Automation Coverage** | 100% |
| **Estimated Execution Time** | 45 minutes (manual) |
| | 5 minutes (automated) |

---

## Known Issues / Defects Found During Test Design

| Defect ID | Title | Status |
|-----------|-------|--------|
| DEFECT_001 | Decimal amounts cause "Internal Server Error" | Open |
| DEFECT_002 | Daily limit not enforced for API calls | Open |
| DEFECT_003 | Transaction history not updated in real-time | Open |

See `ISSUES.md` for detailed defect reports.

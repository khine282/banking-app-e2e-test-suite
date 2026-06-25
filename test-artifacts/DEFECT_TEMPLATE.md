# Defect Reporting Template

Use this template to document defects found during testing. Format: JIRA issue style.

---

## Template

```
DEFECT ID: DEFECT_XXX
Status: Open / In Progress / Resolved / Closed
Severity: Critical / High / Medium / Low
Priority: P0 (Urgent) / P1 (High) / P2 (Medium) / P3 (Low)

========================================
SUMMARY (One-line description)
========================================
[What is the issue in one clear sentence?]

Example:
"Fund transfer fails with amount containing decimals"


========================================
DESCRIPTION
========================================
Detailed explanation of the issue and its impact.

Example:
"When a user attempts to transfer an amount with decimal places (e.g., $10.50),
the system returns an 'Internal Server Error 500' instead of processing the transaction.
This prevents users from transferring amounts that don't round to whole dollars."


========================================
STEPS TO REPRODUCE
========================================
1. [First step]
2. [Second step]
3. [Action that triggers bug]
4. [Observation]

Example:
1. Navigate to Transfer Funds page
2. Login with user: test_user / password123
3. Select source account: ACC001
4. Select recipient account: ACC002
5. Enter amount: 10.50
6. Click "Submit"
7. Observe error message


========================================
EXPECTED BEHAVIOR
========================================
[What should happen if working correctly?]

Example:
"Transfer should process successfully, displaying:
- Confirmation message
- Transaction ID
- Updated balance: Original - 10.50
- Transaction recorded in history"


========================================
ACTUAL BEHAVIOR
========================================
[What actually happened?]

Example:
"System displays:
- HTTP 500 Internal Server Error
- Message: 'Transaction failed'
- Balance unchanged
- No transaction ID generated
- No entry in transaction history"


========================================
ENVIRONMENT
========================================
- URL: [https://parabank.parasoft.com]
- Browser: [Chrome 120.0]
- Operating System: [Windows 11]
- Test Data: [ACC001 balance: $5,000]
- Build/Version: [v1.2.3]


========================================
ATTACHMENTS
========================================
- Screenshot 1: Error message
- Screenshot 2: Browser console showing error
- Video: Recorded test execution
- Selenium Script: Test that reproduces issue


========================================
SEVERITY ASSESSMENT
========================================

Severity: HIGH

Rationale:
- Blocks users from transferring decimal amounts
- Affects currency precision (critical for banking)
- No workaround available
- Impacts user base who transfer fractional dollars


========================================
ROOT CAUSE (Analysis)
========================================
[Developer analysis - optional for QA to suggest]

Possible causes:
- Form input validation: May reject decimal separator
- Backend: Using integer instead of BigDecimal
- API: Rounding logic before processing


========================================
RELATED TEST CASE
========================================
- Test Case ID: TC_010
- Test Name: "Decimal Amount Transfer"
- Coverage: Edge Case Testing


========================================
ACCEPTANCE CRITERIA FOR FIX
========================================
✓ User can transfer amounts with decimals
✓ Correct balance after transfer (including cents)
✓ No rounding errors
✓ Transaction recorded with full precision
✓ Works across multiple decimal values ($0.01, $10.50, $99.99)


========================================
ADDITIONAL NOTES
========================================
- Recurring issue during regression testing
- Impacts international transfers (always have decimals)
- Should be fixed before production release
- May need database migration for decimal precision


========================================
DEFECT HISTORY
========================================
Status: Open
Found By: QA Engineer
Found Date: 2024-01-15
Assigned To: [Developer Name]
Resolution Target: 2024-01-22
```

---

## Defect Severity Guide

### CRITICAL
**Impact**: System unavailable, data loss, security breach
- Transfer executes twice, charges user twice
- Balance corrupts and shows wrong amount
- User funds disappear
- System crashes during transfer

**Action**: Fix immediately, notify management

---

### HIGH
**Impact**: Feature broken, no reasonable workaround
- Transfer fails with valid data
- Cannot process any transfers
- System blocks legitimate users

**Action**: Fix before next release

---

### MEDIUM
**Impact**: Feature partially broken, workaround exists
- Decimal amounts cause rounding errors
- UI displays wrong balance (but actual balance correct)
- Confirmation message unclear

**Action**: Fix in next sprint

---

### LOW
**Impact**: Minor usability issue, no data impact
- Button text unclear
- Spelling error in message
- UI alignment off

**Action**: Fix when convenient

---

## How Banking Hiring Managers Evaluate Defect Management

**Organizations will assess:**

1. **Clarity**: Is the defect understandable? Can someone reproduce it?
   - ✅ Clear steps to reproduce
   - ✅ Expected vs Actual comparison
   - ❌ Vague descriptions like "it doesn't work"

2. **Evidence**: Do you provide proof?
   - ✅ Screenshots showing the error
   - ✅ Test script that reproduces it
   - ✅ Browser console errors
   - ❌ No supporting evidence

3. **Impact**: Do you assess business impact?
   - ✅ "This prevents users from..."
   - ✅ "This could cause..."
   - ❌ Generic severity without justification

4. **Documentation**: Professional presentation?
   - ✅ Well-organized, structured
   - ✅ JIRA/tool compatible format
   - ❌ Unorganized notes

---

## Pro Tips for QA Defect Reporting

### ✅ DO:
- Reproduce issue multiple times before reporting
- Test on multiple browsers/environments
- Provide exact reproduction steps
- Include screenshots/videos
- Suggest possible root causes (for developer efficiency)
- Document expected behavior from requirements
- Assess severity objectively

### ❌ DON'T:
- Report issues without reproducing
- Make assumptions about cause
- Use vague language ("something is broken")
- Forget to include test data details
- Mix multiple defects in one report
- Exaggerate severity
- Report design disagreements as bugs

---

## Example: How Your Report Appears in GitHub Issues

### In GitHub Repository:

```
Title: Fund transfer fails with amount containing decimals

Type: bug
Severity: High
Component: Transfer Feature
Test Case: TC_010

## Description
When a user attempts to transfer $10.50, the system returns HTTP 500 error.

## Steps to Reproduce
1. Navigate to Transfer page
2. Select ACC001 → ACC002
3. Enter amount: 10.50
4. Click Submit

## Expected
- Transfer succeeds
- Balance updates to reflect cents

## Actual
- HTTP 500 Internal Server Error
- Balance unchanged

## Screenshots
[Attached: error-screenshot.png]

## Test Script
```java
transferPage.transfer("ACC001", "ACC002", 10.50);
// FAILS: "Internal Server Error"
```

## Root Cause (suspected)
Backend using Integer instead of BigDecimal for amounts.
```

This format impresses hiring managers because:
- ✅ Professional documentation
- ✅ Clear reproduction steps
- ✅ Evidence (screenshot)
- ✅ Technical analysis
- ✅ Test coverage linking

package com.dbs.banking.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

// ==================== Login Request/Response ====================

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class LoginResponse {
    @JsonProperty("user_id")
    private int userId;
    
    @JsonProperty("auth_token")
    private String authToken;
    
    private String status;
    private String message;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getAuthToken() { return authToken; }
    public void setAuthToken(String authToken) { this.authToken = authToken; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "LoginResponse{userId=" + userId + ", status='" + status + "'}";
    }
}

// ==================== Account Balance ====================

class AccountBalance {
    @JsonProperty("account_id")
    private String accountId;
    
    private double balance;
    private String currency;

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

// ==================== Transfer Request/Response ====================

class TransferRequest {
    @JsonProperty("fromAccountId")
    private String fromAccountId;
    
    @JsonProperty("toAccountId")
    private String toAccountId;
    
    private double amount;

    public String getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(String fromAccountId) { this.fromAccountId = fromAccountId; }

    public String getToAccountId() { return toAccountId; }
    public void setToAccountId(String toAccountId) { this.toAccountId = toAccountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

class TransferResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    
    private String status;
    private double amount;
    private String message;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "TransferResponse{transactionId='" + transactionId + "', status='" + status + "', amount=" + amount + "}";
    }
}

// ==================== Transaction ====================

class Transaction {
    @JsonProperty("transaction_id")
    private String transactionId;
    
    private String type;
    private double amount;
    
    @JsonProperty("transaction_date")
    private String transactionDate;
    
    private String description;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
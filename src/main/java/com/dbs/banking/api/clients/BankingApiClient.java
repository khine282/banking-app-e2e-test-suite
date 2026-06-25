package com.dbs.banking.api.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankingApiClient {
    private static final Logger logger = LoggerFactory.getLogger(BankingApiClient.class);
    private RequestSpecification requestSpec;
    private String baseUrl;

    public BankingApiClient(String baseUrl, RequestSpecification requestSpec) {
        this.baseUrl = baseUrl;
        this.requestSpec = requestSpec;
    }

    public Response login(String username, String password) {
        logger.info("Sending login request for username: {}", username);
        
        String payload = String.format(
            "{\"username\": \"%s\", \"password\": \"%s\"}",
            username, password
        );
        
        Response response = requestSpec
            .body(payload)
            .post(baseUrl + "/api/login");
        
        logger.info("Login response status: {}", response.getStatusCode());
        return response;
    }

    public Response getAccountBalance(String accountId, String authToken) {
        logger.info("Fetching balance for account: {}", accountId);
        
        return requestSpec
            .header("Authorization", "Bearer " + authToken)
            .get(baseUrl + "/api/accounts/" + accountId + "/balance");
    }

    public Response transfer(String fromAccount, String toAccount, double amount, String authToken) {
        logger.info("Transferring {} from {} to {}", amount, fromAccount, toAccount);
        
        String payload = String.format(
            "{\"fromAccountId\": \"%s\", \"toAccountId\": \"%s\", \"amount\": %.2f}",
            fromAccount, toAccount, amount
        );
        
        Response response = requestSpec
            .header("Authorization", "Bearer " + authToken)
            .body(payload)
            .post(baseUrl + "/api/transfer");
        
        logger.info("Transfer response status: {}", response.getStatusCode());
        return response;
    }

    public Response getTransactionHistory(String accountId, String authToken) {
        logger.info("Fetching transaction history for account: {}", accountId);
        
        return requestSpec
            .header("Authorization", "Bearer " + authToken)
            .get(baseUrl + "/api/accounts/" + accountId + "/transactions");
    }

    public Response verifyTransfer(String transactionId, String authToken) {
        logger.info("Verifying transfer with transaction ID: {}", transactionId);
        
        return requestSpec
            .header("Authorization", "Bearer " + authToken)
            .get(baseUrl + "/api/transfer/" + transactionId);
    }
}
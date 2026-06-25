package com.parabank.banking.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApiClientConfig.class);

    public static RequestSpecification getRequestSpec(String baseUrl) {
        logger.info("Initializing RequestSpec with baseUrl: {}", baseUrl);
        
        return new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType("application/json")
            .setAccept("application/json")
            .build();
    }
}

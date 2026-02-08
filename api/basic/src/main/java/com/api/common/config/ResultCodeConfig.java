package com.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.result.code")
public class ResultCodeConfig {
    private int success;
    private int error;
    private int badRequest;

    // Getter and Setter methods
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getBadRequest() {
        return badRequest;
    }

    public void setBadRequest(int badRequest) {
        this.badRequest = badRequest;
    }
}


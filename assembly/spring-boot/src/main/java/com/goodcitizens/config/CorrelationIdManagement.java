package com.goodcitizens.config;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component("correlationIdManagement")
public class CorrelationIdManagement {

    static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();

    public void setCorrelationId(String value) {
        CORRELATION_ID.set(value);
    }

    public String getCorrelationId() {
        return CORRELATION_ID.get();
    }

    @PreDestroy
    public void clear(){
        CORRELATION_ID.remove();
    }
}

package com.goodcitizens.config;

import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Component
public class CorrelationInterceptor extends HandlerInterceptorAdapter {

    final static Logger logger = Logger.getLogger(CorrelationInterceptor.class);

    @Autowired
    private CorrelationIdManagement correlationIdManagement;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler){
        LogUtils.logInfo(logger, LogLevel.CONFIGURATION, LogUtilMsg.CORRELATION_ID_INFO);
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(CorrelationIdManagement.CORRELATION_ID_LOG_VAR_NAME, correlationId);
        correlationIdManagement.setCorrelationId(correlationId);
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex){
        MDC.remove(CorrelationIdManagement.CORRELATION_ID_LOG_VAR_NAME);
        correlationIdManagement.clear();
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(CorrelationIdManagement.CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}

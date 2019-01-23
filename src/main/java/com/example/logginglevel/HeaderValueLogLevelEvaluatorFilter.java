// Copyright (c) 2019 Travelex Ltd

package com.example.logginglevel;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EventEvaluatorBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HeaderValueLogLevelEvaluatorFilter extends EventEvaluatorBase<ILoggingEvent> {

    private static Logger logger = LoggerFactory.getLogger(HeaderValueLogLevelEvaluatorFilter.class);
    private static final String LOGGING_LEVEL_HEADER_NAME = "X-Logging-Level";

    @Override
    public boolean evaluate(ILoggingEvent event) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //If this isn't an HTTP thread, don't filter
        if (requestAttributes == null) return true;
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        String requestedLoggingLevel = servletRequest.getHeader(LOGGING_LEVEL_HEADER_NAME);

        //If not passed header, don't filter.
        if (StringUtils.isEmpty(requestedLoggingLevel)) return true;

        Level requestedMinLevel = Level.toLevel(requestedLoggingLevel);
        Level eventLevel = event.getLevel();

        return eventLevel.isGreaterOrEqual(requestedMinLevel);

    }
}

package com.goodcitizens.service.producer.impl;

import com.goodcitizens.service.config.CorrelationIdManagement;
import com.goodcitizens.service.converter.CitizenTOtoCitizenEventTOConverter;
import com.goodcitizens.service.producer.DeleteCitizenProducer;
import com.goodcitizens.service.streams.CitizenStreams;
import com.goodcitizens.to.CitizenEventTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component("deleteCitizenPublisher")
public class DeleteCitizenProducerImpl implements DeleteCitizenProducer {

    final static Logger logger = Logger.getLogger(DeleteCitizenProducerImpl.class);

    @Autowired
    private CitizenStreams citizenStreams;

    @Autowired
    private CorrelationIdManagement correlationIdManagement;

    @Override
    public void notify(CitizenTO citizenTO) {
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.NOTIFICATION_DELETE);
        CitizenEventTO event = CitizenTOtoCitizenEventTOConverter.convert(citizenTO);
        if(event != null){
            LogUtils.logDebug(logger, LogLevel.BUSINESS, event);
            event.setCorrelationId(correlationIdManagement.getCorrelationId());
            MessageChannel messageChannel = citizenStreams.outboundDeleteCitizens();
            messageChannel.send(MessageBuilder
                    .withPayload(event)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
        }
    }
}

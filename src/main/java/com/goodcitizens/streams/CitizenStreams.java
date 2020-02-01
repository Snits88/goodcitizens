package com.goodcitizens.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component("citizenStreams")
public interface CitizenStreams {
    String OUTPUT_CREATE = "citizens-create-out";
    String OUTPUT_UPDATE = "citizens-update-out";
    String OUTPUT_DELETE = "citizens-delete-out";

    @Output(OUTPUT_CREATE)
    MessageChannel outboundCreateCitizens();

    @Output(OUTPUT_UPDATE)
    MessageChannel outboundUpdateCitizens();

    @Output(OUTPUT_DELETE)
    MessageChannel outboundDeleteCitizens();

}

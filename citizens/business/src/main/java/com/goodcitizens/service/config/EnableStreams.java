package com.goodcitizens.service.config;

import com.goodcitizens.service.streams.CitizenStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(CitizenStreams.class)
public class EnableStreams {
}

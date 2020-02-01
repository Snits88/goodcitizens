package com.goodcitizens.service.producer.impl;

import com.goodcitizens.converter.CitizenTOtoCitizenEventTOConverter;
import com.goodcitizens.service.producer.CreateCitizenProducer;
import com.goodcitizens.streams.CitizenStreams;
import com.goodcitizens.to.CitizenEventTO;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCitizenProducerImplTest {

    @Autowired
    private CreateCitizenProducer createCitizenProducer;

    @Autowired
    private CitizenStreams citizenStreams;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void testWithCreateProducer() throws IOException {
        CitizenTO citizenTO = getCitizenTO();
        CitizenEventTO event = CitizenTOtoCitizenEventTOConverter.convert(citizenTO);
        createCitizenProducer.notify(citizenTO);
        Message<String> received = (Message<String>) messageCollector.forChannel(citizenStreams.outboundCreateCitizens()).poll();
        String result = received.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        CitizenEventTO o = objectMapper.readValue(result,CitizenEventTO.class);
        Assert.assertTrue(StringUtils.equals(o.getName(), event.getName()));
        Assert.assertTrue(StringUtils.equals(o.getSurname(), event.getSurname()));
        Assert.assertTrue(StringUtils.equals(o.getNickname(), event.getNickname()));
        Assert.assertTrue(StringUtils.equals(o.getEmail(), event.getEmail()));
        Assert.assertTrue(StringUtils.equals(o.getCountry(), event.getCountry()));
    }

    private CitizenTO getCitizenTO(){
        CitizenTO citizenTO = new CitizenTO();
        citizenTO.setName("name");
        citizenTO.setSurname("surname");
        citizenTO.setNickname("nickname");
        citizenTO.setEmail("email@email.com");
        citizenTO.setPassword("password");
        citizenTO.setCountry("country");
        return citizenTO;
    }

}

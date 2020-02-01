package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NormalizeInputFieldBRImplTest {

    @TestConfiguration
    static class NormalizeInputFieldBRImplTestContextConfiguration {
        @Bean
        public NormalizeInputFieldBR NormalizeInputFieldBR() {
            return new NormalizeInputFieldBRImpl();
        }
    }

    @Autowired
    private NormalizeInputFieldBR normalizeInputFieldBR;

    @Test
    public void testWithEmptyInput(){
        CitizenTO input = null;
        CitizenTO output = normalizeInputFieldBR.normalize(input);
        Assert.assertNull(output);
    }

    @Test
    public void testWithNotEmptyInput(){
        CitizenTO input = getCitizenTO();
        CitizenTO output = normalizeInputFieldBR.normalize(input);
        Assert.assertNotNull(output);
        Assert.assertTrue(StringUtils.equals(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getName()))), output.getName()));
        Assert.assertTrue(StringUtils.equals(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getSurname()))), output.getSurname()));
        Assert.assertTrue(StringUtils.equals(input.getNickname(), output.getNickname()));
        Assert.assertTrue(StringUtils.equals(input.getEmail(), output.getEmail()));
        Assert.assertTrue(StringUtils.equals(input.getPassword(), output.getPassword()));
        Assert.assertTrue(StringUtils.equals(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getCountry()))), output.getCountry()));
    }


    private CitizenTO getCitizenTO(){
        CitizenTO citizenTO = new CitizenTO();
        citizenTO.setName("name");
        citizenTO.setSurname("surname");
        citizenTO.setNickname("nickname");
        citizenTO.setEmail("email");
        citizenTO.setPassword("password");
        citizenTO.setCountry("country");
        return citizenTO;
    }
}

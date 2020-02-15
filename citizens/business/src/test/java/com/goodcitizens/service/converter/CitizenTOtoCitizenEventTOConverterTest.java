package com.goodcitizens.service.converter;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenEventTO;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CitizenTOtoCitizenEventTOConverterTest {

    @Test
    public void testWithConvertNull(){
        CitizenTO citizenTO = null;
        Citizen result = CitizenTOtoCitizenConveter.convert(citizenTO);
        Assert.assertNull(result);
    }

    @Test
    public void testWithConvertNotNull(){
        CitizenTO citizenTO = getCitizenTO();
        CitizenEventTO result = CitizenTOtoCitizenEventTOConverter.convert(citizenTO);
        Assert.assertNotNull(result);
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(citizenTO.getCountry(), result.getCountry()));
    }

    @Test
    public void testWithConvertNotNullStringFormatCapitalize(){
        CitizenTO citizenTO = getCitizenTO();
        CitizenEventTO result = CitizenTOtoCitizenEventTOConverter.convert(citizenTO);
        Assert.assertNotNull(result);
        Assert.assertTrue(StringUtils.equals(citizenTO.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getCountry(), result.getCountry()));
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

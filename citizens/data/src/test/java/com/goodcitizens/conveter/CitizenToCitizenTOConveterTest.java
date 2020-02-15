package com.goodcitizens.conveter;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CitizenToCitizenTOConveterTest {

    @Test
    public void testWithConvertNull(){
        CitizenTO citizenTO = null;
        Citizen result = CitizenTOtoCitizenConveter.convert(citizenTO);
        Assert.assertNull(result);
    }

    @Test
    public void testWithConvertNotNull(){
        Citizen citizen = getCitizen();
        CitizenTO result = CitizenToCitizenTOConveter.convert(citizen);
        Assert.assertNotNull(result);
        Assert.assertTrue(StringUtils.equals(citizen.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equals(citizen.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equals(citizen.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equals(citizen.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equals(citizen.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equals(citizen.getCountry(), result.getCountry()));
    }

    private Citizen getCitizen(){
        Citizen citizen = new Citizen();
        citizen.setName("name");
        citizen.setSurname("surname");
        citizen.setNickname("nickname");
        citizen.setEmail("email");
        citizen.setPassword("password");
        citizen.setCountry("country");
        return citizen;
    }

}

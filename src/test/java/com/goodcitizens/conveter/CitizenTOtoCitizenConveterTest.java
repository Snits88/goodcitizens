package com.goodcitizens.conveter;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CitizenTOtoCitizenConveterTest {

    @Test
    public void testWithConvertNull(){
        CitizenTO citizenTO = null;
        Citizen result = CitizenTOtoCitizenConveter.convert(citizenTO);
        Assert.assertNull(result);
    }

    @Test
    public void testWithConvertNotNull(){
        CitizenTO citizenTO = getCitizenTO();
        Citizen result = CitizenTOtoCitizenConveter.convert(citizenTO);
        Assert.assertNotNull(result);
        Assert.assertTrue(StringUtils.equals(citizenTO.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equals(citizenTO.getCountry(), result.getCountry()));
        Assert.assertNotNull(result.getCreateDate());
        Assert.assertNotNull(result.getUpdateDate());
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

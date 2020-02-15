package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.to.CitizenTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateUpdateCitizenFieldsBRImplTest {

    @TestConfiguration
    static class CreateUpdateCitizenFieldsBRImplTestContextConfiguration {
        @Bean
        public CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR() {
            return new CreateUpdateCitizenFieldsBRImpl();
        }
    }

    private static String stringTooLong = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";

    @Autowired
    private CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR;

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenEmpty(){
        CitizenTO citizenTO = null;
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }


    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoName(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setName(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoSurname(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setSurname(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoNickname(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setNickname(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoEmail(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setEmail(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoPassword(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setPassword(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNoCountry(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setCountry(null);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }


    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNameTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setName(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenSurnameTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setSurname(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenNicknameTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setNickname(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenEmailTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setEmail(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenPasswordTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setPassword(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenCountryTooLong(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setCountry(stringTooLong);
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenEmailNotValid(){
        CitizenTO citizenTO = getValidCitizenTO();
        citizenTO.setEmail("emailnotvalid");
        createUpdateCitizenFieldsBR.validateCitizenTO(citizenTO);
    }


    private CitizenTO getValidCitizenTO(){
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

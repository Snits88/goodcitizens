package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.CreateCitizenService;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.service.businessrules.NicknameEmailCreateBR;
import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.service.businessrules.impl.CreateUpdateCitizenFieldsBRImpl;
import com.goodcitizens.service.businessrules.impl.NicknameEmailCreateBRImpl;
import com.goodcitizens.service.businessrules.impl.NormalizeInputFieldBRImpl;
import com.goodcitizens.service.producer.CreateCitizenProducer;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
public class CreateCitizenServiceImplTest {

    @TestConfiguration
    static class CreateCitizenServiceImplTestContextConfiguration {

        @Bean
        public CreateCitizenService createCitizenService() {
            return new CreateCitizenServiceImpl();
        }

        @Bean
        public CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR() {
            return new CreateUpdateCitizenFieldsBRImpl();
        }

        @Bean
        public NicknameEmailCreateBR nicknameEmailCreateBR() {
            return new NicknameEmailCreateBRImpl();
        }

        @Bean
        public NormalizeInputFieldBR normalizeInputFieldBR() {
            return new NormalizeInputFieldBRImpl();
        }

    }

    @Autowired
    CreateCitizenService createCitizenService;

    @Autowired
    private CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR;

    @Autowired
    private NicknameEmailCreateBR nicknameEmailCreateBR;

    @Autowired
    private NormalizeInputFieldBR normalizeInputFieldBR;

    @MockBean
    private ReadCitizensListService readCitizensListService;

    @MockBean
    private CitizenPersistenceApi citizenPersistenceApi;

    @MockBean
    private CreateCitizenProducer CreateCitizenProducer;

    @Test
    public void testWithValidCitizen() {
        CitizenListTO citizenListTO = getCitizenListTOEmpty();
        Mockito.when(readCitizensListService.readList(Mockito.any())).thenReturn(citizenListTO);
        CitizenTO input = getCitizenTO();
        Mockito.when(citizenPersistenceApi.createCitizen(Mockito.any())).thenReturn(input);
        CitizenTO result = createCitizenService.create(input);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getCitizenId());
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equals(input.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equals(input.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equals(input.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getCountry(), result.getCountry()));
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithNullCitizen() {
        CitizenTO input = null;
        CitizenTO result = createCitizenService.create(input);
    }

    @Test(expected = CitizenGenericExpection.class)
    public void testWithNotValidCitizen() {
        CitizenTO input = getCitizenNotValidTO();
        CitizenTO result = createCitizenService.create(input);
    }

    private CitizenTO getCitizenNotValidTO(){
        // no surname
        CitizenTO citizenTO = new CitizenTO();
        citizenTO.setName("name");
        citizenTO.setNickname("nickname");
        citizenTO.setEmail("email@email.com");
        citizenTO.setPassword("password");
        citizenTO.setCountry("country");
        return citizenTO;
    }

    private CitizenTO getCitizenTO(){
        CitizenTO citizenTO = new CitizenTO();
        citizenTO.setCitizenId(1L);
        citizenTO.setName("name");
        citizenTO.setSurname("surname");
        citizenTO.setNickname("nickname");
        citizenTO.setEmail("email@email.com");
        citizenTO.setPassword("password");
        citizenTO.setCountry("country");
        return citizenTO;
    }

    private CitizenListTO getCitizenListTOEmpty(){
        CitizenListTO citizenListTO = new CitizenListTO();
        citizenListTO.setTotalItems(0L);
        citizenListTO.setCitizens(new ArrayList<>());
        return citizenListTO;
    }

}

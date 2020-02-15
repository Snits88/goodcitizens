package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.UpdateCitizenService;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.service.businessrules.NicknameEmailUpdateBR;
import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.service.businessrules.impl.CreateUpdateCitizenFieldsBRImpl;
import com.goodcitizens.service.businessrules.impl.NicknameEmailUpdateBRImpl;
import com.goodcitizens.service.businessrules.impl.NormalizeInputFieldBRImpl;
import com.goodcitizens.service.producer.UpdateCitizenProducer;
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
import java.util.List;

@RunWith(SpringRunner.class)
public class UpdateCitizenServiceImplTest {

    @TestConfiguration
    static class UpdateCitizenServiceImplTestContextConfiguration {

        @Bean
        public UpdateCitizenService updateCitizenService() {
            return new UpdateCitizenServiceImpl();
        }

        @Bean
        public CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR() {
            return new CreateUpdateCitizenFieldsBRImpl();
        }

        @Bean
        public NicknameEmailUpdateBR nicknameEmailUpdateBR() {
            return new NicknameEmailUpdateBRImpl();
        }

        @Bean
        public NormalizeInputFieldBR normalizeInputFieldBR() {
            return new NormalizeInputFieldBRImpl();
        }

    }

    @Autowired
    UpdateCitizenService updateCitizenService;

    @Autowired
    private CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR;

    @Autowired
    private NicknameEmailUpdateBR nicknameEmailUpdateBR;

    @Autowired
    private NormalizeInputFieldBR normalizeInputFieldBR;

    @MockBean
    private ReadCitizensListService readCitizensListService;

    @MockBean
    private CitizenPersistenceApi citizenPersistenceApi;

    @MockBean
    private UpdateCitizenProducer updateCitizenProducer;

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCodeNotCorrect(){
        String code = "abc2";
        CitizenTO citizenTO = getValidCitizenTO();
        updateCitizenService.update(code,citizenTO);
    }

    @Test(expected = CitizenNotFoundExpection.class)
    public void testWithCodeCorrectAndEntityNotFound(){
        String code = "123";
        CitizenTO citizenTO = getValidCitizenTO();
        Mockito.when(citizenPersistenceApi.readByCode(code)).thenReturn(null);
        updateCitizenService.update(code,citizenTO);
    }


    @Test
    public void testWithValidCitizenAndNickNameEmailChangedNotAlreadyUsed() {
        String code = "1";
        CitizenTO input = getValidCitizenTO();
        Mockito.when(citizenPersistenceApi.readByCode(code)).thenReturn(input);
        CitizenListTO citizenListTO = getCitizenListTOOnlyUpdateCitizen();
        Mockito.when(readCitizensListService.readList(Mockito.any())).thenReturn(citizenListTO);
        Mockito.when(citizenPersistenceApi.updateCitizen(Mockito.any(), Mockito.any())).thenReturn(input);
        CitizenTO result = updateCitizenService.update(code,input);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getCitizenId());
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getName(), result.getName()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getSurname(), result.getSurname()));
        Assert.assertTrue(StringUtils.equals(input.getNickname(), result.getNickname()));
        Assert.assertTrue(StringUtils.equals(input.getEmail(), result.getEmail()));
        Assert.assertTrue(StringUtils.equals(input.getPassword(), result.getPassword()));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(input.getCountry(), result.getCountry()));
    }

    private CitizenTO getValidCitizenTO(){
        CitizenTO citizenTO = new CitizenTO();
        citizenTO.setName("name");
        citizenTO.setSurname("surname");
        citizenTO.setNickname("nickname");
        citizenTO.setEmail("email@email.com");
        citizenTO.setPassword("password");
        citizenTO.setCountry("country");
        citizenTO.setCitizenId(1L);
        return citizenTO;
    }

    private CitizenListTO getCitizenListTOOnlyUpdateCitizen(){
        CitizenListTO citizenListTO = new CitizenListTO();
        List<CitizenTO> citizens = new ArrayList<>();
        citizens.add(getValidCitizenTO());
        citizenListTO.setTotalItems(Long.valueOf(citizens.size()));
        citizenListTO.setCitizens(citizens);
        return citizenListTO;
    }
}

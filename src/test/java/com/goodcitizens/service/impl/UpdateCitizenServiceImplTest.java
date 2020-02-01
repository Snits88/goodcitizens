package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
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
import java.util.Optional;

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
    private CitizenBasicCrudRepository citizenRepository;

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
        Mockito.when(citizenRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        updateCitizenService.update(code,citizenTO);
    }


    @Test
    public void testWithValidCitizenAndNickNameEmailChangedNotAlreadyUsed() {
        String code = "1";
        Mockito.when(citizenRepository.findById(Mockito.any())).thenReturn(getValidOptional());
        CitizenListTO citizenListTO = getCitizenListTOOnlyUpdateCitizen();
        Mockito.when(readCitizensListService.readList(Mockito.any())).thenReturn(citizenListTO);
        Citizen citizen = getCitizen();
        Mockito.when(citizenRepository.save(Mockito.any())).thenReturn(citizen);
        CitizenTO input = getValidCitizenTO();
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

    private Optional<Citizen> getValidOptional(){
        Optional<Citizen> findOnDB = Optional.of(getCitizen());
        return findOnDB;
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
        citizenListTO.setTotalItems(citizens.size());
        citizenListTO.setCitizens(citizens);
        return citizenListTO;
    }

    private Citizen getCitizen(){
        Citizen citizen = new Citizen();
        citizen.setName("name");
        citizen.setSurname("surname");
        citizen.setNickname("nickname");
        citizen.setEmail("email@email.com");
        citizen.setPassword("password");
        citizen.setCountry("country");
        citizen.setCitizenId(1L);
        return citizen;
    }

}

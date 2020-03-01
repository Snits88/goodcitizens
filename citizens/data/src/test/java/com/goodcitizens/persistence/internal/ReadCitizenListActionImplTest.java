package com.goodcitizens.persistence.internal;

import com.goodcitizens.persistence.api.internal.ReadCitizenListAction;
import com.goodcitizens.persistence.api.internal.ReadCitizenListActionImpl;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
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
public class ReadCitizenListActionImplTest {

    @TestConfiguration
    static class ReadCitizenListActionImplTestContextConfiguration {

        @Bean
        public ReadCitizenListAction readCitizenListAction() {
            return new ReadCitizenListActionImpl();
        }

    }

    @Autowired
    private ReadCitizenListAction readCitizenListAction;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;


    @Test
    public void testWithReadCitizenListAction(){
        List<Citizen> result = getCitizens();
        Mockito.when(citizenRepository.readCitizensList(Mockito.any())).thenReturn(result);
        CitizenListTO citizenListTO = readCitizenListAction.read(Mockito.any());
        Assert.assertNotNull(citizenListTO);
        Assert.assertNotNull(citizenListTO.getCitizens());
        Assert.assertTrue(citizenListTO.getCitizens().size() ==  result.size());
        for (Citizen citizen: result) {
            boolean check = false;
            for (CitizenTO citizenTO: citizenListTO.getCitizens()) {
                if(StringUtils.equals(citizen.getName(), citizenTO.getName()) &&
                        StringUtils.equals(citizen.getSurname(), citizenTO.getSurname()) &&
                        StringUtils.equals(citizen.getNickname(), citizenTO.getNickname()) &&
                        StringUtils.equals(citizen.getEmail(), citizenTO.getEmail()) &&
                        StringUtils.equals(citizen.getPassword(), citizenTO.getPassword()) &&
                        StringUtils.equals(citizen.getCountry(), citizenTO.getCountry())){
                    check = true;
                }
            }
            Assert.assertTrue(check);
        }
    }

    private List<Citizen> getCitizens(){
        List<Citizen> citizens = new ArrayList<>();

        Citizen citizen1 = new Citizen();
        citizen1.setName("name1");
        citizen1.setSurname("surname1");
        citizen1.setNickname("nickname1");
        citizen1.setEmail("email1");
        citizen1.setPassword("password1");
        citizen1.setCountry("country1");

        citizens.add(citizen1);
        return citizens;
    }

    private CitizenListTO getCitizenListTO(){
        CitizenListTO citizenListTO = new CitizenListTO();
        citizenListTO.setTotalItems(0L);
        citizenListTO.setCitizens(new ArrayList<>());
        return citizenListTO;
    }

}

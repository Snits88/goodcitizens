package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.service.businessrules.NicknameEmailUpdateBR;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class NicknameEmailUpdateBRImplTest {

    @TestConfiguration
    static class NicknameEmailUpdateBRImplTestContextConfiguration {
        @Bean
        public NicknameEmailUpdateBR nicknameEmailUpdateBR() {
            return new NicknameEmailUpdateBRImpl();
        }
    }

    @Autowired
    private NicknameEmailUpdateBR NicknameEmailUpdateBR;

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCitizenListTOMultilpeEntities(){
        CitizenListTO citizenListTO = getValidCitizens();
        NicknameEmailUpdateBR.validUpdate(1L, citizenListTO);

    }


    private CitizenListTO getValidCitizens(){
        CitizenListTO citizens = new CitizenListTO();
        List<CitizenTO> citizensList = new ArrayList<>();

        CitizenTO citizen1 = new CitizenTO();
        citizen1.setName("name1");
        citizen1.setSurname("surname1");
        citizen1.setNickname("nickname1");
        citizen1.setEmail("email1");
        citizen1.setPassword("password1");
        citizen1.setCountry("country1");
        citizen1.setCitizenId(1L);

        CitizenTO citizen2 = new CitizenTO();
        citizen2.setName("name2");
        citizen2.setSurname("surname2");
        citizen2.setNickname("nickname2");
        citizen2.setEmail("email2");
        citizen2.setPassword("password2");
        citizen2.setCountry("country2");
        citizen2.setCitizenId(2L);

        citizensList.add(citizen1);
        citizensList.add(citizen2);

        citizens.setTotalItems(Long.valueOf(citizensList.size()));
        citizens.setCitizens(citizensList);
        return citizens;
    }

}

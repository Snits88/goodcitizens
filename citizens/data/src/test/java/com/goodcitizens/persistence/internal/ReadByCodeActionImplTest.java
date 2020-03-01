package com.goodcitizens.persistence.internal;

import com.goodcitizens.persistence.api.internal.ReadByCodeAction;
import com.goodcitizens.persistence.api.internal.ReadByCodeActionImpl;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class ReadByCodeActionImplTest {

    @TestConfiguration
    static class ReadByCodeActionImplTestContextConfiguration {

        @Bean
        public ReadByCodeAction readByCodeAction() {
            return new ReadByCodeActionImpl();
        }

    }

    @Autowired
    private ReadByCodeAction readByCodeAction;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;


    @Test
    public void testWithReadByCodeAction(){
        String codeS = "1";
        Citizen result = getCitizen();
        Mockito.when(citizenRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(result));
        CitizenTO output = readByCodeAction.read(codeS);
        Assert.assertNotNull(output);
        Assert.assertEquals("Wrong Name", output.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", output.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", output.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", output.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", output.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", output.getCountry(), result.getCountry());
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

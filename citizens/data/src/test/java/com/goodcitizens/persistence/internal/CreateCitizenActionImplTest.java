package com.goodcitizens.persistence.internal;

import com.goodcitizens.persistence.api.internal.CreateCitizenAction;
import com.goodcitizens.persistence.api.internal.CreateCitizenActionImpl;
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

@RunWith(SpringRunner.class)
public class CreateCitizenActionImplTest {

    @TestConfiguration
    static class CreateCitizenActionImplTestContextConfiguration {

        @Bean
        public CreateCitizenAction createCitizenAction() {
            return new CreateCitizenActionImpl();
        }

    }

    @Autowired
    private CreateCitizenAction createCitizenAction;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;

    @Test
    public void testWithCreateCitizenAction(){
        Citizen result = getCitizen();
        Mockito.when(citizenRepository.save(Mockito.any())).thenReturn(result);
        CitizenTO output = createCitizenAction.create(Mockito.any());
        Assert.assertNotNull(output);
        Assert.assertEquals("Wrong Name", output.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", output.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", output.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", output.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", output.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", output.getCountry(), result.getCountry());
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

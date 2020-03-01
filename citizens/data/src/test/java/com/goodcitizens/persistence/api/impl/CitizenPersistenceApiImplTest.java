package com.goodcitizens.persistence.api.impl;

import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.persistence.api.internal.*;
import com.goodcitizens.to.CitizenListTO;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class)
public class CitizenPersistenceApiImplTest {

    @TestConfiguration
    static class CitizenPersistenceApiImplTestContextConfiguration {

        @Bean
        public CitizenPersistenceApi citizenPersistenceApi() {
            return new CitizenPersistenceApiImpl();
        }

    }

    @Autowired
    private CitizenPersistenceApi citizenPersistenceApi;

    @MockBean
    private UpdateCitizenAction updateCitizenAction;

    @MockBean
    private DeleteCitizenAction deleteCitizenAction;

    @MockBean
    private CreateCitizenAction createCitizenAction;

    @MockBean
    private ReadCitizenListAction readCitizenListAction;

    @MockBean
    private ReadByCodeAction readByCodeAction;

    @MockBean
    private CountAction countAction;


    @Test
    public void testWithUpdateCitizenAction(){
        CitizenTO result = getCitizenTO();
        Mockito.when(updateCitizenAction.update(Mockito.any(), Mockito.any())).thenReturn(result);
        CitizenTO output = citizenPersistenceApi.updateCitizen(Mockito.any(), Mockito.any());
        Assert.assertNotNull(output);
        Assert.assertEquals("Wrong Name", output.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", output.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", output.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", output.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", output.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", output.getCountry(), result.getCountry());
    }

    @Test
    public void testWithDeleteCitizenAction(){
        Mockito.doNothing().when(deleteCitizenAction);
        citizenPersistenceApi.deleteCitizen(Mockito.any());
    }

    @Test
    public void testWithCreateCitizenAction(){
        CitizenTO result = getCitizenTO();
        Mockito.when(createCitizenAction.create(Mockito.any())).thenReturn(result);
        CitizenTO output = citizenPersistenceApi.createCitizen(Mockito.any());
        Assert.assertNotNull(output);
        Assert.assertEquals("Wrong Name", output.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", output.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", output.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", output.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", output.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", output.getCountry(), result.getCountry());
    }

    @Test
    public void testWithReadCitizenListAction(){
        Mockito.when(readCitizenListAction.read(Mockito.any())).thenReturn(getCitizenListTO());
        CitizenListTO citizenListTO = citizenPersistenceApi.readCitizensList(Mockito.any());
        Assert.assertNotNull(citizenListTO);
        Assert.assertNotNull(citizenListTO.getCitizens());
    }

    @Test
    public void testWithReadByCodeAction(){
        CitizenTO result = getCitizenTO();
        Mockito.when(readByCodeAction.read(Mockito.any())).thenReturn(result);
        CitizenTO output = citizenPersistenceApi.readByCode(Mockito.any());
        Assert.assertNotNull(output);
        Assert.assertEquals("Wrong Name", output.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", output.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", output.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", output.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", output.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", output.getCountry(), result.getCountry());
    }

    @Test
    public void testWithCountAction(){
        Long result = 4l;
        Mockito.when(countAction.count()).thenReturn(result);
        Long output = citizenPersistenceApi.count();
        Assert.assertEquals(result, output);
    }

    private CitizenListTO getCitizenListTO(){
        CitizenListTO citizenListTO = new CitizenListTO();
        citizenListTO.setTotalItems(0L);
        citizenListTO.setCitizens(new ArrayList<>());
        return citizenListTO;
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

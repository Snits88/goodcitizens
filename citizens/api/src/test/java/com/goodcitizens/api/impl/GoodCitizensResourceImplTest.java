package com.goodcitizens.api.impl;

import com.goodcitizens.api.GoodCitizensResource;
import com.goodcitizens.service.CreateCitizenService;
import com.goodcitizens.service.DeleteCitizenService;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.UpdateCitizenService;
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
public class GoodCitizensResourceImplTest {

    @TestConfiguration
    static class GoodCitizensResourceImplTestContextConfiguration {

        @Bean
        public GoodCitizensResource GoodCitizensResource() {
            return new GoodCitizensResourceImpl();
        }
    }

    @Autowired
    private GoodCitizensResource goodCitizensResource;

    @MockBean
    private ReadCitizensListService readCitizensListService;

    @MockBean
    private CreateCitizenService createCitizenService;

    @MockBean
    private UpdateCitizenService updateCitizenService;

    @MockBean
    private DeleteCitizenService deleteCitizenService;


   @Test
    public void testWithReadCitizensListResource() {
        Mockito.when(readCitizensListService.readList(Mockito.any())).thenReturn(getCitizenListTO());
        CitizenListTO citizenListTO = goodCitizensResource.readCitizensList("","","","","", "", "", false, false);
        Assert.assertNotNull(citizenListTO);
        Assert.assertNotNull(citizenListTO.getCitizens());
    }

    @Test
    public void testWithCreateCitizensResource() {
        CitizenTO result = getCitizenTO();
        Mockito.when(createCitizenService.create(Mockito.any())).thenReturn(result);
        CitizenTO citizenTO = goodCitizensResource.createCitizen(Mockito.any());
        Assert.assertNotNull(citizenTO);
        Assert.assertEquals("Wrong Name", citizenTO.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", citizenTO.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", citizenTO.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", citizenTO.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", citizenTO.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", citizenTO.getCountry(), result.getCountry());
    }


    @Test
    public void testWithUpdateCitizensResource() {
        CitizenTO result = getCitizenTO();
        Mockito.when(updateCitizenService.update(Mockito.any(), Mockito.any())).thenReturn(result);
        CitizenTO citizenTO = goodCitizensResource.updateCitizen(Mockito.any(), Mockito.any());
        Assert.assertNotNull(citizenTO);
        Assert.assertEquals("Wrong Name", citizenTO.getName(), result.getName());
        Assert.assertEquals("Wrong Surname", citizenTO.getSurname(), result.getSurname());
        Assert.assertEquals("Wrong Nickname", citizenTO.getNickname(), result.getNickname());
        Assert.assertEquals("Wrong Email", citizenTO.getEmail(), result.getEmail());
        Assert.assertEquals("Wrong Password", citizenTO.getPassword(), result.getPassword());
        Assert.assertEquals("Wrong Country", citizenTO.getCountry(), result.getCountry());
    }

    @Test
    public void testWithDeleteCitizensResource() {
        Mockito.doNothing().when(deleteCitizenService);
        goodCitizensResource.deleteCitizen(Mockito.any());
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

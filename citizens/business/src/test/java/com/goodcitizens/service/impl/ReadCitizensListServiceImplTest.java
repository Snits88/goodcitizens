package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.config.properties.OrderingProperties;
import com.goodcitizens.service.config.properties.PaginationProperties;
import com.goodcitizens.to.CitizenFilterTO;
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
public class ReadCitizensListServiceImplTest {

    @TestConfiguration
    static class ReadCitizensListServiceImplTestContextConfiguration {

        @Bean
        public ReadCitizensListService readCitizensListService() {
            return new ReadCitizensListServiceImpl();
        }

        @Bean
        public OrderingProperties orderingProperties() {
            return new OrderingProperties();
        }

        @Bean
        public PaginationProperties paginationProperties() {
            return new PaginationProperties();
        }
    }

    @Autowired
    private ReadCitizensListService readCitizensListService;

    @MockBean
    private CitizenPersistenceApi citizenPersistenceApi;

    @Test(expected = CitizenGenericExpection.class)
    public void textWithCitizenFilterTONull(){
        CitizenFilterTO citizenFilterTO = null;
        readCitizensListService.readList(citizenFilterTO);
    }

    @Test
    public void textWithCitizenFilterTONotNull(){
        CitizenFilterTO citizenFilterTO = getCitizenFilterTO();
        CitizenListTO citizens = getCitizens();
        Mockito.when(citizenPersistenceApi.readCitizensList(Mockito.any())).thenReturn(citizens);
        Mockito.when(citizenPersistenceApi.count()).thenReturn(2L);
        CitizenListTO result =  readCitizensListService.readList(citizenFilterTO);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getTotalItems() == citizens.getCitizens().size());
        Assert.assertTrue(result.getCitizens().size() == citizens.getCitizens().size());
        for (CitizenTO citizen: citizens.getCitizens()) {
            boolean check = false;
            for (CitizenTO citizenTO: result.getCitizens()) {
                if(StringUtils.equalsIgnoreCase(citizen.getName(), citizenTO.getName()) &&
                        StringUtils.equalsIgnoreCase(citizen.getSurname(), citizenTO.getSurname()) &&
                        StringUtils.equalsIgnoreCase(citizen.getNickname(), citizenTO.getNickname()) &&
                        StringUtils.equalsIgnoreCase(citizen.getEmail(), citizenTO.getEmail()) &&
                        StringUtils.equalsIgnoreCase(citizen.getPassword(), citizenTO.getPassword()) &&
                        StringUtils.equalsIgnoreCase(citizen.getCountry(), citizenTO.getCountry())){
                    check = true;
                }
            }
            Assert.assertTrue(check);
        }
    }


    private CitizenFilterTO getCitizenFilterTO(){
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setName("name");
        citizenFilterTO.setEnableOR(false);
        return citizenFilterTO;
    }

    private CitizenListTO getCitizens(){
        CitizenListTO citizenListTO = new CitizenListTO();

        List<CitizenTO> citizens = new ArrayList<>();

        CitizenTO citizen1 = new CitizenTO();
        citizen1.setName("name1");
        citizen1.setSurname("surname1");
        citizen1.setNickname("nickname1");
        citizen1.setEmail("email1");
        citizen1.setPassword("password1");
        citizen1.setCountry("country1");

        CitizenTO citizen2 = new CitizenTO();
        citizen2.setName("name2");
        citizen2.setSurname("surname2");
        citizen2.setNickname("nickname2");
        citizen2.setEmail("email2");
        citizen2.setPassword("password2");
        citizen2.setCountry("country2");

        citizens.add(citizen1);
        citizens.add(citizen2);

        citizenListTO.setTotalItems(Long.valueOf(citizens.size()));
        citizenListTO.setCitizens(citizens);
        return citizenListTO;
    }
}

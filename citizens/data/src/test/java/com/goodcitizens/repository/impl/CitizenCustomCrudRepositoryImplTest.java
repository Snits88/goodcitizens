package com.goodcitizens.repository.impl;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.persistence.repository.CitizenCustomCrudRepository;
import com.goodcitizens.persistence.repository.impl.CitizenCustomCrudRepositoryImpl;
import com.goodcitizens.to.CitizenFilterTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/data-h2.sql")
public class CitizenCustomCrudRepositoryImplTest {

    @SpringBootApplication
    @EntityScan( basePackages = {"com.goodcitizens.persistence.model"} )
    @EnableJpaRepositories (basePackages = {"com.goodcitizens.persistence.repository"})
    static class CitizenCustomCrudRepositoryImplTestConfiguration{

        @Bean
        public CitizenCustomCrudRepository citizenCustomCrudRepository(){
            return new CitizenCustomCrudRepositoryImpl();
        }

    }

    @Autowired
    private CitizenBasicCrudRepository repository;

    @Test
    public void testWithReadCitizenListNoFilter(){
        List<Citizen> result = repository.readCitizensList(getBasicCitizenFilterTO());
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(result.size(),4);
    }

    @Test
    public void testWithReadCitizenListANDFilter(){
        List<Citizen> result = repository.readCitizensList(getNameANDSurnameCitizenFilterTO());
        Assert.assertTrue(CollectionUtils.isEmpty(result));
    }

    @Test
    public void testWithReadCitizenListORFilter(){
        CitizenFilterTO citizenFilterTO = getNameANDSurnameCitizenFilterTO();
        citizenFilterTO.setEnableOR(true);
        List<Citizen> result = repository.readCitizensList(citizenFilterTO);
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(result.size(), 3);
    }



    private CitizenFilterTO getNameANDSurnameCitizenFilterTO(){
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setName("Angelo");
        citizenFilterTO.setSurname("Palmer");
        citizenFilterTO.setNickname("TheOne");
        citizenFilterTO.setEnableOR(false);
        citizenFilterTO.setOrderBy("name");
        citizenFilterTO.setOffset("0");
        citizenFilterTO.setLimit("20");
        return citizenFilterTO;
    }


    private CitizenFilterTO getBasicCitizenFilterTO(){
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setEnableOR(false);
        citizenFilterTO.setOrderAsc(false);
        citizenFilterTO.setOrderBy("name");
        citizenFilterTO.setOffset("0");
        citizenFilterTO.setLimit("20");
        return citizenFilterTO;
    }

}

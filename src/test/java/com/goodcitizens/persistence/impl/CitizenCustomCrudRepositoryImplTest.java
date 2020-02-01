package com.goodcitizens.persistence.impl;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenFilterTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CitizenCustomCrudRepositoryImplTest {

    @Autowired
    private CitizenBasicCrudRepository citizenBasicCrudRepository;

    @Test
    public void testWithReadCitizenListNoFilter(){
        List<Citizen> result = citizenBasicCrudRepository.readCitizensList(getBasicCitizenFilterTO());
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(result.size(),4);
    }

    @Test
    public void testWithReadCitizenListANDFilter(){
        List<Citizen> result = citizenBasicCrudRepository.readCitizensList(getNameANDSurnameCitizenFilterTO());
        Assert.assertTrue(CollectionUtils.isEmpty(result));
    }

    @Test
    public void testWithReadCitizenListORFilter(){
        CitizenFilterTO citizenFilterTO = getNameANDSurnameCitizenFilterTO();
        citizenFilterTO.setEnableOR(true);
        List<Citizen> result = citizenBasicCrudRepository.readCitizensList(citizenFilterTO);
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(result.size(), 3);
    }



    private CitizenFilterTO getNameANDSurnameCitizenFilterTO(){
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setName("Angelo");
        citizenFilterTO.setSurname("Palmer");
        citizenFilterTO.setNickname("TheOne");
        citizenFilterTO.setEnableOR(false);
        return citizenFilterTO;
    }


    private CitizenFilterTO getBasicCitizenFilterTO(){
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setEnableOR(false);
        return citizenFilterTO;
    }
}

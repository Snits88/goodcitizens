package com.goodcitizens.persistence.internal;

import com.goodcitizens.persistence.api.internal.CountAction;
import com.goodcitizens.persistence.api.internal.CountActionImpl;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
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
public class CountActionImplTest {

    @TestConfiguration
    static class CountActionImplTestContextConfiguration {

        @Bean
        public CountAction countAction() {
            return new CountActionImpl();
        }

    }

    @Autowired
    private CountAction countAction;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;

    @Test
    public void testWithCountAction(){
        Long result = 4l;
        Mockito.when(citizenRepository.count()).thenReturn(result);
        Long output = countAction.count();
        Assert.assertEquals(result, output);
    }

}

package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.service.DeleteCitizenService;
import com.goodcitizens.service.producer.DeleteCitizenProducer;
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
public class DeleteCitizenServiceImplTest {

    @TestConfiguration
    static class DeleteCitizenServiceImplTestContextConfiguration {

        @Bean
        public DeleteCitizenService deleteCitizenService() {
            return new DeleteCitizenServiceImpl();
        }

    }

    @Autowired
    private DeleteCitizenService deleteCitizenService;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;

    @MockBean
    private DeleteCitizenProducer deleteCitizenProducer;

    @Test(expected = CitizenGenericExpection.class)
    public void testWithCodeNotCorrect(){
        String code = "abc2";
        deleteCitizenService.delete(code);
    }

    @Test(expected = CitizenNotFoundExpection.class)
    public void testWithCodeCorrectAndEntityNotFound(){
        String code = "123";
        Mockito.when(citizenRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        deleteCitizenService.delete(code);
    }

}

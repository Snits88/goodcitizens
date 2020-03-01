package com.goodcitizens.persistence.internal;

import com.goodcitizens.persistence.api.internal.DeleteCitizenAction;
import com.goodcitizens.persistence.api.internal.DeleteCitizenActionImpl;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DeleteCitizenActionImplTest {

    @TestConfiguration
    static class DeleteCitizenActionImplTestContextConfiguration {

        @Bean
        public DeleteCitizenAction deleteCitizenAction() {
            return new DeleteCitizenActionImpl();
        }

    }

    @Autowired
    private DeleteCitizenAction deleteCitizenAction;

    @MockBean
    private CitizenBasicCrudRepository citizenRepository;

    @Test
    public void testWithDeleteCitizenAction(){
        String code = "1";
        Mockito.doNothing().when(citizenRepository);
        deleteCitizenAction.delete(code);
    }

}

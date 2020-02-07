package com.goodcitizens.persistence.api;

import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;

public interface CitizenPersistenceApi {

    Long count();

    CitizenTO readByCode(String code);

    CitizenListTO readCitizensList(CitizenFilterTO searchFilter);

    CitizenTO createCitizen(CitizenTO citizenTO);

    CitizenTO updateCitizen(String code, CitizenTO citizenTO);

    void deleteCitizen(String code);

}

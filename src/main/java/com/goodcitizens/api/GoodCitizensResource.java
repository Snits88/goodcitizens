package com.goodcitizens.api;

import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;

public interface GoodCitizensResource {

    CitizenListTO readCitizensList(String name, String surname, String email, String country, boolean enableOR);

    CitizenTO createCitizen(CitizenTO citizenTO);

    CitizenTO updateCitizen(String code, CitizenTO citizenTO);

    void deleteCitizen(String code);

}

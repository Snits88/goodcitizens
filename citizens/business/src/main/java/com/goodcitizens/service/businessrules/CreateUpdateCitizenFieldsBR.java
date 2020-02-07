package com.goodcitizens.service.businessrules;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface CreateUpdateCitizenFieldsBR {

    void validateCitizenTO(CitizenTO citizenTO);

}

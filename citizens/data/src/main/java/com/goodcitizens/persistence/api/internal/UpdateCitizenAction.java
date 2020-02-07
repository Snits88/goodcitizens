package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface UpdateCitizenAction {

    CitizenTO update(String code, CitizenTO citizenTO);

}

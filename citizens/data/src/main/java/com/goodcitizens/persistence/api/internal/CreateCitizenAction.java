package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface CreateCitizenAction {

    CitizenTO create(CitizenTO citizen);
}

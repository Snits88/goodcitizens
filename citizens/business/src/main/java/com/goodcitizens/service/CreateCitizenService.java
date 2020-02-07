package com.goodcitizens.service;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface CreateCitizenService {

    CitizenTO create(CitizenTO input);

}

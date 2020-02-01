package com.goodcitizens.service.producer;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface CreateCitizenProducer {

    void notify(CitizenTO citizenTO);

}

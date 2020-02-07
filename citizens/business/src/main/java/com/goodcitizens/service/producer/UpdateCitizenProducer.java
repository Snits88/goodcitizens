package com.goodcitizens.service.producer;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface UpdateCitizenProducer {

    void notify(CitizenTO citizenTO);
}

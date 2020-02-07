package com.goodcitizens.service.producer;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface DeleteCitizenProducer {

    void notify(CitizenTO citizenTO);

}

package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;

@FunctionalInterface
public interface ReadCitizenListAction {

    CitizenListTO read(CitizenFilterTO searchFilter);

}

package com.goodcitizens.service;

import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;

@FunctionalInterface
public interface ReadCitizensListService {

    CitizenListTO readList(CitizenFilterTO searchFilter);

}

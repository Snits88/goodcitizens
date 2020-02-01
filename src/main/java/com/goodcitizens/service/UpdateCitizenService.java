package com.goodcitizens.service;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface UpdateCitizenService {
    
    CitizenTO update(String code, CitizenTO input);

}

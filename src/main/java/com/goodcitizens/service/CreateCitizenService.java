package com.goodcitizens.service;

import com.goodcitizens.to.CitizenTO;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface CreateCitizenService {

    CitizenTO create(CitizenTO input);

}

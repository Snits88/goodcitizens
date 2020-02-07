package com.goodcitizens.persistence.repository;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenFilterTO;

import java.util.List;

public interface CitizenCustomCrudRepository {

    List<Citizen> readCitizensList(CitizenFilterTO citizenFilterTO);

}
